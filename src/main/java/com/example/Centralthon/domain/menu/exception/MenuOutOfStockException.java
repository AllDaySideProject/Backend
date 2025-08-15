package com.example.Centralthon.domain.menu.exception;

import com.example.Centralthon.global.exception.BaseException;

public class MenuOutOfStockException extends BaseException {
    public MenuOutOfStockException() {super(MenuErrorCode.MENU_OUT_OF_STOCK);}
}
