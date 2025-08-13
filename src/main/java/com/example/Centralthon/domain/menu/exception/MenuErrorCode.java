package com.example.Centralthon.domain.menu.exception;

import com.example.Centralthon.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuErrorCode implements BaseResponseCode {
    MENU_NOT_FOUND("MENU_NOT_FOUND_404_1",404,"존재하지 않는 메뉴 입니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
