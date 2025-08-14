package com.example.Centralthon.domain.order.web.dto;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderReq {
    List<@Valid OrderItemListReq> items;
}
