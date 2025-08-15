package com.example.Centralthon.domain.order.service;

import com.example.Centralthon.domain.order.web.dto.CompleteOrderReq;
import com.example.Centralthon.domain.order.web.dto.CreateOrderReq;
import com.example.Centralthon.domain.order.web.dto.CreateOrderRes;

public interface OrderService {
    CreateOrderRes orderMenus(CreateOrderReq orderReq);
    void completePickUp(CompleteOrderReq completeOrderReq);
}
