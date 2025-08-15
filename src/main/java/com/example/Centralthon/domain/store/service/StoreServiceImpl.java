package com.example.Centralthon.domain.store.service;

import com.example.Centralthon.domain.store.entity.Store;
import com.example.Centralthon.domain.store.repository.StoreRepository;
import com.example.Centralthon.domain.store.web.dto.NearbyStoresRes;
import com.example.Centralthon.domain.store.web.dto.StoreMenusRes;
import com.example.Centralthon.global.util.geo.BoundingBox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.Centralthon.global.util.geo.GeoUtils.calculateBoundingBox;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<NearbyStoresRes> nearbyStores(double lat, double lng) {
        LocalDateTime now = LocalDateTime.now();

        // 사용자 위치를 기반으로 2km 반경의 Bounding Box 계산
        BoundingBox bbox = calculateBoundingBox(lat, lng, 2.0);

        List<Store> stores = storeRepository.findNearbyStores(lat, lng, now,
                bbox.minLat(), bbox.maxLat(), bbox.minLng(), bbox.maxLng());

        return stores.stream()
                .map(store -> NearbyStoresRes.from(store))
                .toList();
    }

    @Override
    public StoreMenusRes getStoreMenus(Long storeId) {
        return null;
    }
}
