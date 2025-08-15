package com.example.Centralthon.domain.order.exception;

import com.example.Centralthon.global.exception.BaseException;

public class OrderExpiredException extends BaseException {
    public OrderExpiredException() {
        super(OrderErrorCode.ORDER_EXPIRED);
    }
}
