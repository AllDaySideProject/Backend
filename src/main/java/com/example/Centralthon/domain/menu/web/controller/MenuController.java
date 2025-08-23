package com.example.Centralthon.domain.menu.web.controller;

import com.example.Centralthon.domain.menu.service.MenuService;

import com.example.Centralthon.domain.menu.web.dto.*;
import com.example.Centralthon.domain.menu.web.dto.GetRecommendedMenusReq;
import com.example.Centralthon.global.external.ai.web.dto.GetTipReq;
import com.example.Centralthon.global.external.ai.web.dto.GetTipRes;
import com.example.Centralthon.global.response.SuccessResponse;
import com.example.Centralthon.global.validation.Latitude;
import com.example.Centralthon.global.validation.Longitude;
import jakarta.validation.Valid;

import com.example.Centralthon.domain.menu.web.dto.NearbyMenusRes;
import com.example.Centralthon.domain.menu.web.dto.StoresByMenuRes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController implements MenuApi {
    private final MenuService menuService;

    //맞춤 추천용 메뉴 목록 조회
    @GetMapping("")
    @Override
    public ResponseEntity<SuccessResponse<List<NearbyMenusRes>>> nearbyMenus(
            @RequestParam("lat") @Latitude Double lat,
            @RequestParam("lng") @Longitude Double lng) {

        List<NearbyMenusRes> menus = menuService.nearbyMenus(lat,lng);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(menus));
    }

    //특정 메뉴를 판매 하는 가게 조회
    @GetMapping("/stores")
    @Override
    public ResponseEntity<SuccessResponse<List<StoresByMenuRes>>> storesByMenu(
            @RequestParam("name") String name,
            @RequestParam("lat") @Latitude Double lat,
            @RequestParam("lng") @Longitude Double lng) {

        List<StoresByMenuRes> stores = menuService.storesByMenu(name,lat,lng);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(stores));
    }

    //메뉴 상세 조회
    @PostMapping("/details")
    @Override
    public ResponseEntity<SuccessResponse<List<MenuDetailsRes>>> details(@RequestBody @Valid MenuIdsReq menus){
        List<MenuDetailsRes> menuList = menuService.details(menus);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(menuList));
    }

    // 컨셉별 메뉴 추천
    @PostMapping("/recommend")
    @Override
    public ResponseEntity<SuccessResponse<List<NearbyMenusRes>>> recommend(@RequestBody @Valid GetRecommendedMenusReq getRecommendedMenusReq){
        List<NearbyMenusRes> menusList = menuService.getRecommendedMenus(getRecommendedMenusReq);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(menusList));
    }

    // 알뜰 반찬 팁 조회
    @PostMapping("/tips")
    @Override
    public ResponseEntity<SuccessResponse<List<GetTipRes>>> getTips(@RequestBody @Valid GetTipReq getTipReq){
        List<GetTipRes> tips = menuService.getTips(getTipReq);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(tips));
    }
}
