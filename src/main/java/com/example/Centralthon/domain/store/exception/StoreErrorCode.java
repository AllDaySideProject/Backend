package com.example.Centralthon.domain.store.exception;

import com.example.Centralthon.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseResponseCode {
    STORE_NOT_FOUND("STORE_NOT_FOUND_404_1",404,"존재하지 않는 가게 입니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
