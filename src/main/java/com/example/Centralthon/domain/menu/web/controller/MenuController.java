package com.example.Centralthon.domain.menu.web.controller;

import com.example.Centralthon.domain.menu.service.MenuService;
import com.example.Centralthon.domain.menu.web.dto.NearbyMenusRes;
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
    public ResponseEntity<SuccessResponse<Map<String, Object>>> nearbyMenus(
            @RequestParam("lat") Double lat,
            @RequestParam("lng") Double lng) {

        List<NearbyMenusRes> menus = menuService.nearbyMenus(lat,lng);
        Map<String, Object> data = Map.of("menus", menus);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(data));
    }
}
