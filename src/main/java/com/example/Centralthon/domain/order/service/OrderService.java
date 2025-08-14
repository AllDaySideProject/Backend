package com.example.Centralthon.domain.order.service;

import com.example.Centralthon.domain.order.web.dto.OrderReq;
import com.example.Centralthon.domain.order.web.dto.OrderRes;

import java.util.List;

public interface OrderService {
    OrderRes orderMenus(OrderReq orderReq);
}
