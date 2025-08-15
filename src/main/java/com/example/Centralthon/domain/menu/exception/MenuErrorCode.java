package com.example.Centralthon.domain.menu.exception;

import com.example.Centralthon.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuErrorCode implements BaseResponseCode {
    MENU_NOT_FOUND("MENU_NOT_FOUND_404_1",404,"존재하지 않는 메뉴 입니다."),
    MENU_EXPIRED("MENU_EXPIRED_400_1", 400, "판매 마감된 메뉴입니다."),
    MENU_OUT_OF_STOCK("MENU_OUT_OF_STOCK_400_2", 400, "재고가 부족합니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
