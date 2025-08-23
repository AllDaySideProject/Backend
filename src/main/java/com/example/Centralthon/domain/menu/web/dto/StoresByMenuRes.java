package com.example.Centralthon.domain.menu.web.dto;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.menu.entity.enums.MenuCategory;
import com.example.Centralthon.domain.store.entity.Store;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public record StoresByMenuRes(
        String storeName,
        double distance,
        @JsonUnwrapped MenuItemRes menuItem
) {
    public static StoresByMenuRes from(Menu menu, double distance) {
        return new StoresByMenuRes(
                menu.getStore().getName(),
                distance,
                MenuItemRes.from(menu)
        );
    }
}
