package com.example.Centralthon.domain.order.web.controller;

import com.example.Centralthon.domain.order.service.OrderService;
import com.example.Centralthon.domain.order.web.dto.OrderReq;
import com.example.Centralthon.domain.order.web.dto.OrderRes;
import com.example.Centralthon.global.response.SuccessResponse;
import com.example.Centralthon.global.response.code.SuccessResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<SuccessResponse<OrderRes>> createOrder(@RequestBody @Valid OrderReq orderReq) {
        OrderRes orderRes = orderService.orderMenus(orderReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.of(orderRes, SuccessResponseCode.SUCCESS_CREATED));
    }
}
