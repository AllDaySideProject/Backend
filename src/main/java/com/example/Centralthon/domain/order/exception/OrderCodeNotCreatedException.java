package com.example.Centralthon.domain.order.exception;

import com.example.Centralthon.global.exception.BaseException;

public class OrderCodeNotCreatedException extends BaseException {
    public OrderCodeNotCreatedException() {
        super(OrderErrorCode.ORDER_CODE_NOT_CREATED);
    }
}
