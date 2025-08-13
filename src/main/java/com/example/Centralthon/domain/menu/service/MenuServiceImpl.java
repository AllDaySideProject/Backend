package com.example.Centralthon.domain.menu.service;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.menu.exception.MenuNotFoundException;
import com.example.Centralthon.domain.menu.repository.MenuRepository;
import com.example.Centralthon.domain.menu.web.dto.*;
import com.example.Centralthon.domain.store.entity.Store;
import com.example.Centralthon.global.util.geo.BoundingBox;
import com.example.Centralthon.global.util.geo.GeoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.Centralthon.global.util.geo.GeoUtils.calculateBoundingBox;
import static com.example.Centralthon.global.util.geo.GeoUtils.calculateDistance;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    // 주어진 위도, 경도를 중심으로 반경 2km 이내의 메뉴 조회
    private List<Menu> findMenusWithinRadius(double latitude, double longitude) {
        LocalDateTime now = LocalDateTime.now();

        // 사용자 위치를 기반으로 2km 반경의 Bounding Box 계산
        BoundingBox bbox = calculateBoundingBox(latitude, longitude, 2.0);

        // 좌표 전달
        List<Menu> menus = menuRepository.findNearbyMenus(
                latitude, longitude, now,
                bbox.minLat(), bbox.maxLat(), bbox.minLng(), bbox.maxLng()
        );
        return menus;
    }

    @Override
    @Transactional(readOnly=true)
    public List<NearbyMenusRes> nearbyMenus(double latitude, double longitude) {
        List<Menu> menus = findMenusWithinRadius(latitude, longitude);

        // 중복 메뉴 제거
        Map<String, Menu> uniqueMenus = new LinkedHashMap<>();
        for (Menu menu : menus) {
            uniqueMenus.putIfAbsent(menu.getName(), menu);
        }

        return uniqueMenus.values().stream()
                .map(NearbyMenusRes::from)
                .toList();
    }

    @Override
    @Transactional(readOnly=true)
    public List<StoresByMenuRes> storesByMenu(String name, double lat, double lng) {
        List<Menu> menus = findMenusWithinRadius(lat, lng);

        // 이름이 일치하는 메뉴만 필터링, 가격 오름차순 정렬
        List<Menu> filtered = menus.stream()
                .filter(m -> m.getName().equals(name))
                .sorted(Comparator.comparingInt(Menu::getSalePrice))
                .toList();

        return filtered.stream()
                .map(menu -> StoresByMenuRes.from(
                        menu,
                        GeoUtils.calculateDistance(lat, lng, menu.getStore().getLatitude(), menu.getStore().getLongitude())
                ))
                .toList();
    }

    @Override
    @Transactional(readOnly=true)
    public List<MenuDetailsRes> details(MenuIdsReq menus) {
        List<Menu> menuList = menuRepository.findAllById(menus.getMenuIds());

        if (menuList.isEmpty()) {throw new MenuNotFoundException();}

        return menuList.stream()
                .map(menu -> MenuDetailsRes.from(menu))
                .toList();
    }
}
