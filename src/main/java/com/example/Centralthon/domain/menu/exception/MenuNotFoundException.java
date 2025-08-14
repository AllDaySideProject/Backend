package com.example.Centralthon.domain.menu.exception;

import com.example.Centralthon.global.exception.BaseException;

public class MenuNotFoundException extends BaseException {
    public MenuNotFoundException() {super(MenuErrorCode.MENU_NOT_FOUND);}
}
