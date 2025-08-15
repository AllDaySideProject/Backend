package com.example.Centralthon.domain.store.exception;

import com.example.Centralthon.global.exception.BaseException;

public class StoreNotFoundException extends BaseException {
    public StoreNotFoundException( ) {
        super(StoreErrorCode.STORE_NOT_FOUND);
    }
}
