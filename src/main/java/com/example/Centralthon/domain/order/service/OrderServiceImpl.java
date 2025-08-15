package com.example.Centralthon.domain.order.service;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.menu.exception.MenuExpiredException;
import com.example.Centralthon.domain.menu.exception.MenuNotFoundException;
import com.example.Centralthon.domain.menu.exception.MenuOutOfStockException;
import com.example.Centralthon.domain.menu.repository.MenuRepository;
import com.example.Centralthon.domain.order.entity.Order;
import com.example.Centralthon.domain.order.entity.OrderItem;
import com.example.Centralthon.domain.order.exception.OrderCodeNotCreatedException;
import com.example.Centralthon.domain.order.exception.OrderExpiredException;
import com.example.Centralthon.domain.order.exception.OrderNotFoundException;
import com.example.Centralthon.domain.order.repository.OrderItemRepository;
import com.example.Centralthon.domain.order.repository.OrderRepository;
import com.example.Centralthon.domain.order.web.dto.CompleteOrderReq;
import com.example.Centralthon.domain.order.web.dto.CreateOrderReq;
import com.example.Centralthon.domain.order.web.dto.CreateOrderRes;
import com.example.Centralthon.domain.order.web.dto.OrderItemListReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Lazy @Autowired
    private OrderServiceImpl selfProxy;

    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public CreateOrderRes orderMenus(CreateOrderReq orderReq) {
        Map<Long, Integer> orderList = new HashMap<>();

        for (OrderItemListReq orderItemListReq : orderReq.getItems()) {
            orderList.merge(orderItemListReq.getMenuId(), orderItemListReq.getCount(), Integer::sum);
        }

        // 메뉴 id가 없을 경우 -> MenuNotFoundException
        List<Menu> menuList = menuRepository.findAllByIdWithStore(orderList.keySet());
        if(menuList.size() != orderList.keySet().size()) throw new MenuNotFoundException();

        // 최종 결제 금액 계산
        int totalPrice = 0;
        LocalDateTime now = LocalDateTime.now();
        for (Menu menu : menuList) {
            // 메뉴 마감 시간이 지났을 경우 -> MenuExpiredException
            if(menu.getDeadline().isBefore(now)) throw new MenuExpiredException();

            // 메뉴 재고가 부족할 경우 -> MenuOutOfStockException
            if(menu.getQuantity()<orderList.get(menu.getId())) throw new MenuOutOfStockException();

            // 오버플로우 방지를 위한 addExact, multiplyExact 사용
            totalPrice = Math.addExact(totalPrice, Math.multiplyExact(menu.getSalePrice(), orderList.get(menu.getId())));
        }

        // 픽업 코드를 통해 주문 생성
        Order order = createOrderWithUniqueCode(totalPrice);

        List<OrderItem> orderItemList = new ArrayList<>();
        Set<Long> storeIds = new LinkedHashSet<>();
        for(Menu menu : menuList) {
            int count = orderList.get(menu.getId());
            orderItemList.add(OrderItem.toEntity(order, menu, count));
            storeIds.add(menu.getStore().getId()); // fetch join : 추가 쿼리 없음

            // 재고 차감
            menu.setQuantity(menu.getQuantity()-count);
        }
        orderItemRepository.saveAll(orderItemList);

        return CreateOrderRes.of(order, new ArrayList<>(storeIds));
    }

    @Override
    @Transactional
    public void completePickUp(CompleteOrderReq completeOrderReq){
        Order order = orderRepository.findByPickUpCode(completeOrderReq.getCode()).orElseThrow(OrderNotFoundException::new);
        if(order.isPickedUp()) throw new OrderExpiredException();
        order.completeOrder();
    }

    /**
     * 같은 트랙잭션에서 실패하면 rollback-only 상태로 인해 정상 커밋이 불가능하므로,
     * 새 트랜잭션을 열어 새로운 픽업 코드를 저장하도록 시도
     * -> 새 트랜잭션을 열기 위해 @Transactional(propagation = Propagation.REQUIRES_NEW) 사용
     * -> @Transactional(propagation = Propagation.REQUIRES_NEW)는 기존 트랜잭션을 중지시키고, 새 트랜잭션 시작
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Order saveOrder(String code, int totalPrice){
        Order order = Order.toEntity(code, totalPrice);
        return orderRepository.save(order);
    }

    private Order createOrderWithUniqueCode(int totalPrice) {
        for(int i = 0; i < 10; i++) {
            String code = generatePickUpCode();
            try{
                // 프록시를 적용해주지 않으면, this.saveOrder가 실행되어 트랙잭션이 실행되지 않음
                return selfProxy.saveOrder(code, totalPrice);
            } catch (DataIntegrityViolationException e) {
                // 다음 실행 진행
            }
        }
        throw new OrderCodeNotCreatedException();
    }

    /**
     * 픽업 코드(영문 두자리 + 숫자 네자리) 생성 메서드
     */
    private String generatePickUpCode() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        char[] codeArray = new char[6];
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String number = "0123456789";

        for (int i = 0; i < 2; i++) {
            char alpha = alphabet.charAt(random.nextInt(alphabet.length()));
            codeArray[i] = alpha;
        }

        for (int i = 0; i < 4; i++) {
            char num = number.charAt(random.nextInt(number.length()));
            codeArray[i + 2] = num;
        }

        return new String(codeArray);
    }
}
