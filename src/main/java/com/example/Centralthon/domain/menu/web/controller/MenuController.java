package com.example.Centralthon.domain.menu.web.controller;

import com.example.Centralthon.domain.menu.service.MenuService;

import com.example.Centralthon.domain.menu.web.dto.*;
import com.example.Centralthon.global.response.SuccessResponse;
import jakarta.validation.Valid;

import com.example.Centralthon.domain.menu.web.dto.NearbyMenusRes;
import com.example.Centralthon.domain.menu.web.dto.StoresByMenuRes;
import com.example.Centralthon.global.response.SuccessResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    //맞춤 추천용 메뉴 목록 조회
    @GetMapping("")
    public ResponseEntity<SuccessResponse<List<NearbyMenusRes>>> nearbyMenus(
            @RequestParam("lat") Double lat,
            @RequestParam("lng") Double lng) {

        List<NearbyMenusRes> menus = menuService.nearbyMenus(lat,lng);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(menus));
    }

    //특정 메뉴를 판매 하는 가게 조회
    @GetMapping("/stores")
    public ResponseEntity<SuccessResponse<List<StoresByMenuRes>>> storesByMenu(
            @RequestParam("name") String name,
            @RequestParam("lat") Double lat,
            @RequestParam("lng") Double lng) {

        List<StoresByMenuRes> stores = menuService.storesByMenu(name,lat,lng);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(stores));
    }

    //메뉴 상세 조회
    @PostMapping("/details")
    public ResponseEntity<SuccessResponse<List<MenuDetailsRes>>> details(@RequestBody @Valid MenuIdsReq menus){
        List<MenuDetailsRes> menuList = menuService.details(menus);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(menuList));
    }
}
