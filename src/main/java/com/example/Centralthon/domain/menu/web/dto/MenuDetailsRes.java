package com.example.Centralthon.domain.menu.web.dto;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.menu.entity.enums.MenuCategory;
import com.example.Centralthon.domain.store.entity.Store;

public record MenuDetailsRes(
        long menuId,
        String name,
        MenuCategory category,
        int costPrice,
        int salePrice,
        String storeName,
        int quantity
) {
    public static MenuDetailsRes from(Menu menu, int quantity) {
        Store store = menu.getStore();
        return new MenuDetailsRes(
                menu.getId(),
                menu.getName(),
                menu.getCategory(),
                menu.getCostPrice(),
                menu.getSalePrice(),
                store.getName(),
                quantity
        );
    }
}
