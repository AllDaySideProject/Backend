package com.example.Centralthon.domain.menu.service;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.menu.exception.MenuNotFoundException;
import com.example.Centralthon.domain.menu.repository.MenuRepository;
import com.example.Centralthon.domain.menu.web.dto.NearbyMenusRes;
import com.example.Centralthon.global.util.geo.BoundingBox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.Centralthon.global.util.geo.GeoUtils.calculateBoundingBox;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    @Override
    public List<NearbyMenusRes> nearbyMenus(double latitude, double longitude) {
        LocalDateTime now = LocalDateTime.now();

        // 사용자 위치를 기반으로 2km 반경의 Bounding Box 계산
        BoundingBox bbox = calculateBoundingBox(latitude, longitude, 2.0);

        // 좌표 전달
        List<Menu> menus = menuRepository.findNearbyMenus(
                latitude, longitude, now,
                bbox.minLat(), bbox.maxLat(), bbox.minLng(), bbox.maxLng()
        );
        if (menus.isEmpty()) {throw new MenuNotFoundException();}

        return menus.stream()
                .map(NearbyMenusRes::from)
                .toList();
    }
}
