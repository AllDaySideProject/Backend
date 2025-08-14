package com.example.Centralthon.domain.store.web.controller;

import com.example.Centralthon.domain.store.service.StoreService;
import com.example.Centralthon.domain.store.web.dto.NearbyStoresRes;
import com.example.Centralthon.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    // 근처 가게 위치 목록 조회
    @GetMapping("")
    public ResponseEntity<SuccessResponse<List<NearbyStoresRes>>> nearbyStores(
            @RequestParam("lat") Double lat,
            @RequestParam("lng") Double lng){

        List<NearbyStoresRes> stores = storeService.nearbyStores(lat, lng);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(stores));
    }
}
