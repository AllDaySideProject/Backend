package com.example.Centralthon.domain.store.web.dto;

import com.example.Centralthon.domain.store.entity.Store;

public record NearbyStoresRes(
        Long storeId,
        Double lat,
        Double lng
) {
    public static NearbyStoresRes from(Store store){
        return new NearbyStoresRes(
                store.getId(),
                store.getLatitude(),
                store.getLongitude()
        );
    }
}
