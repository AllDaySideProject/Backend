package com.example.Centralthon.domain.order.web.controller;

import com.example.Centralthon.domain.order.service.OrderService;
import com.example.Centralthon.domain.order.web.dto.CreateOrderReq;
import com.example.Centralthon.domain.order.web.dto.CreateOrderRes;
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
    public ResponseEntity<SuccessResponse<CreateOrderRes>> createOrder(@RequestBody @Valid CreateOrderReq orderReq) {
        CreateOrderRes createOrderRes = orderService.orderMenus(orderReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.of(createOrderRes, SuccessResponseCode.SUCCESS_CREATED));
    }
}
