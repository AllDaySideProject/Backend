package com.example.Centralthon.domain.route.web.controller;

import com.example.Centralthon.domain.route.service.RouteService;
import com.example.Centralthon.domain.route.web.dto.RouteReq;
import com.example.Centralthon.domain.route.web.dto.RouteRes;
import com.example.Centralthon.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @PostMapping("/directions")
    public ResponseEntity<SuccessResponse<RouteRes>> findOptimalPath(@Valid @RequestBody RouteReq req) {
        RouteRes res = routeService.findOptimalPath(req);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.from(res));
    }
}
