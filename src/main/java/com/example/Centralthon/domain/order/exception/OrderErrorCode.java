package com.example.Centralthon.domain.order.exception;

import com.example.Centralthon.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderErrorCode implements BaseResponseCode {
    CODE_NOT_CREATED("ORDER_500_1",500,"서버에서 유일한 픽업 코드를 생성하지 못했습니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
