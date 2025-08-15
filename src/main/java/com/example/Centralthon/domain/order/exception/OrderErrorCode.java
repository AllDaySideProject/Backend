package com.example.Centralthon.domain.order.exception;

import com.example.Centralthon.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderErrorCode implements BaseResponseCode {
    ORDER_EXPIRED("ORDER_400_1", 400, "만료된 주문입니다."),
    ORDER_NOT_FOUND("ORDER_404_1", 404, "존재하지 않는 주문입니다."),
    ORDER_CODE_NOT_CREATED("ORDER_500_1",500,"서버에서 유일한 픽업 코드를 생성하지 못했습니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
