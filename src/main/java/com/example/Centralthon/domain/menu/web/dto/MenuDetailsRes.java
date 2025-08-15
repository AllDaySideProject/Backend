package com.example.Centralthon.domain.menu.web.dto;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.menu.entity.enums.MenuCategory;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record MenuDetailsRes(
        @JsonUnwrapped MenuItemRes item,
        String storeName
) {
    public static MenuDetailsRes from(Menu menu) {
        return new MenuDetailsRes(
                MenuItemRes.from(menu),
                menu.getStore().getName()
        );
    }
}
