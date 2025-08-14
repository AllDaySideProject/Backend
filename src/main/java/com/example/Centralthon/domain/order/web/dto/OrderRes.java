package com.example.Centralthon.domain.order.web.dto;

import com.example.Centralthon.domain.order.entity.Order;

public record OrderRes(
        String code
) {
    public static OrderRes from(Order order) {
        return new OrderRes(order.getPickUpCode());
    }
}
