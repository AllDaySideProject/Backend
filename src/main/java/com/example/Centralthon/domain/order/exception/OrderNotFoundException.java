package com.example.Centralthon.domain.order.exception;

import com.example.Centralthon.global.exception.BaseException;

public class OrderNotFoundException extends BaseException {
    public OrderNotFoundException() {
        super(OrderErrorCode.ORDER_NOT_FOUND);
    }
}
