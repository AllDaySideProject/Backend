package com.example.Centralthon.domain.order.exception;

import com.example.Centralthon.global.exception.BaseException;

public class CodeNotCreatedException extends BaseException {
    public CodeNotCreatedException() {
        super(OrderErrorCode.CODE_NOT_CREATED);
    }
}
