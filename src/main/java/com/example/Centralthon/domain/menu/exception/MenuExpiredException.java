package com.example.Centralthon.domain.menu.exception;

import com.example.Centralthon.global.exception.BaseException;

public class MenuExpiredException extends BaseException {
    public MenuExpiredException() {super(MenuErrorCode.MENU_EXPIRED);}
}
