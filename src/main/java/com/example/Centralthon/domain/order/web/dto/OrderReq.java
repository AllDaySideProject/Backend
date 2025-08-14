package com.example.Centralthon.domain.order.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderReq {
    @NotEmpty(message = "주문 항목은 빈 배열일 수 없습니다.")
    List<@Valid OrderItemListReq> items;
}
