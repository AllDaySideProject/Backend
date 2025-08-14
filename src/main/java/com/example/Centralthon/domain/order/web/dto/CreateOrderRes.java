package com.example.Centralthon.domain.order.web.dto;

import com.example.Centralthon.domain.order.entity.Order;

import java.util.List;

public record CreateOrderRes(
        String code,
        List<Long> storeList
) {
    public static CreateOrderRes of(Order order, List<Long> storeList) {
        return new CreateOrderRes(order.getPickUpCode(), storeList);
    }
}
