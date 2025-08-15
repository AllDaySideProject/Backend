package com.example.Centralthon.domain.store.web.dto;

import com.example.Centralthon.domain.menu.web.dto.MenuItemRes;
import com.example.Centralthon.domain.menu.web.dto.MenuItemRes;
import com.example.Centralthon.domain.store.entity.Store;
import com.example.Centralthon.domain.store.entity.enums.StoreCategory;

import java.util.List;

public record StoreMenusRes(
        String name,
        StoreCategory category,
        double distance,
        List<MenuItemRes> menus
) {
    public static StoreMenusRes from(Store store, double distance, List<MenuItemRes> menuItems) {
        return new StoreMenusRes(
                store.getName(),
                store.getCategory(),
                distance,
                menuItems
        );
    }
}
