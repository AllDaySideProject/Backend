package com.example.Centralthon.domain.store.service;

import com.example.Centralthon.domain.store.web.dto.NearbyStoresRes;

import java.util.List;

public interface StoreService {
    List<NearbyStoresRes> nearbyStores(double lat, double lng);

}
