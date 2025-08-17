package com.example.Centralthon.domain.route.exception;

import com.example.Centralthon.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RouteErrorCode implements BaseResponseCode {
    ROUTE_SEGMENT_MISSING("ROUTE_500_1", 500, "서버에서 세그먼트가 누락되었습니다."),
    ROUTE_NOT_CREATED("ROUTE_500_2",500,"서버에서 보행자 경로를 생성하지 못했습니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
