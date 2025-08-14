package com.example.Centralthon.domain.order.service;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.menu.exception.MenuNotFoundException;
import com.example.Centralthon.domain.menu.repository.MenuRepository;
import com.example.Centralthon.domain.order.entity.Order;
import com.example.Centralthon.domain.order.entity.OrderItem;
import com.example.Centralthon.domain.order.repository.OrderItemRepository;
import com.example.Centralthon.domain.order.repository.OrderRepository;
import com.example.Centralthon.domain.order.web.dto.OrderReq;
import com.example.Centralthon.domain.order.web.dto.OrderRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrderRes orderMenus(List<OrderReq> orderReqList) {
        Map<Long, Integer> orderList = new HashMap<>();

        for (OrderReq orderReq : orderReqList) {
            orderList.merge(orderReq.getMenuId(), orderReq.getCount(), Integer::sum);
        }

        // 메뉴 id가 없을 경우 -> MenuNotFoundException
        List<Menu> menuList = menuRepository.findAllById(orderList.keySet());
        if(menuList.size() != orderList.keySet().size()) {
            throw new MenuNotFoundException();
        }

        // 최종 결제 금액 계산
        int totalPrice = 0;
        for (Menu menu : menuList) {
            // 오버플로우 방지를 위한 addExact, multiplyExact 사용
            totalPrice = Math.addExact(totalPrice, Math.multiplyExact(menu.getSalePrice(), orderList.get(menu.getId())));
        }

        // 픽업 코드 생성
        String code = generatePickUpCode();

        Order order = Order.toEntity(code, totalPrice);
        orderRepository.save(order);

        List<OrderItem> orderItemList = new ArrayList<>();
        for(Menu menu : menuList) {
            OrderItem orderItem = OrderItem.toEntity(order, menu, orderList.get(menu.getId()));
            orderItemList.add(orderItem);
        }
        orderItemRepository.saveAll(orderItemList);

        return OrderRes.from(order);
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
