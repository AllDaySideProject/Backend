package com.example.Centralthon.domain.menu.service;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.menu.exception.MenuNotFoundException;
import com.example.Centralthon.domain.menu.repository.MenuRepository;
import com.example.Centralthon.domain.menu.web.dto.NearbyMenusRes;
import com.example.Centralthon.global.util.geo.BoundingBox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.example.Centralthon.global.util.geo.GeoUtils.calculateBoundingBox;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    @Override
    @Transactional(readOnly=true)
    public List<NearbyMenusRes> nearbyMenus(double latitude, double longitude) {
        LocalDateTime now = LocalDateTime.now();

        // 사용자 위치를 기반으로 2km 반경의 Bounding Box 계산
        BoundingBox bbox = calculateBoundingBox(latitude, longitude, 2.0);

        // 좌표 전달
        List<Menu> menus = menuRepository.findNearbyMenus(
                latitude, longitude, now,
                bbox.minLat(), bbox.maxLat(), bbox.minLng(), bbox.maxLng()
        );

        // 중복 메뉴 제거
        Map<String, Menu> uniqueMenus = new LinkedHashMap<>();
        for (Menu menu : menus) {
            uniqueMenus.putIfAbsent(menu.getName(), menu);
        }

        return uniqueMenus.values().stream()
                .map(NearbyMenusRes::from)
                .toList();
    }
}
