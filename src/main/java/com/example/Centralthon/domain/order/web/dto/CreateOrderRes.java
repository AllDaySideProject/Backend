package com.example.Centralthon.domain.order.web.dto;

import com.example.Centralthon.domain.order.entity.Order;

public record CreateOrderRes(
        String code
) {
    public static CreateOrderRes from(Order order) {
        return new CreateOrderRes(order.getPickUpCode());
    }
}
