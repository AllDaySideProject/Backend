package com.example.Centralthon.domain.store.web.controller;

import com.example.Centralthon.domain.order.web.controller.OrderApi;
import com.example.Centralthon.domain.store.service.StoreService;
import com.example.Centralthon.domain.store.web.dto.NearbyStoresRes;
import com.example.Centralthon.domain.store.web.dto.StoreMenusRes;
import com.example.Centralthon.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController implements StoreApi {
    private final StoreService storeService;

    // 근처 가게 위치 목록 조회
    @GetMapping("")
    @Override
    public ResponseEntity<SuccessResponse<List<NearbyStoresRes>>> nearbyStores(
            @RequestParam("lat") Double lat,
            @RequestParam("lng") Double lng){

        List<NearbyStoresRes> stores = storeService.nearbyStores(lat, lng);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(stores));
    }

    // 가게 판매 메뉴 목록 조회
    @GetMapping("/{storeId}/menus")
    @Override
    public ResponseEntity<SuccessResponse<StoreMenusRes>> getStoreMenus(
            @PathVariable Long storeId,
            @RequestParam("lat") Double lat,
            @RequestParam("lng") Double lng){

        StoreMenusRes menus = storeService.getStoreMenus(storeId, lat, lng);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(menus));
    }

}
