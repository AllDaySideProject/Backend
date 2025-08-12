package com.example.Centralthon.domain.menu.web.dto;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.menu.entity.enums.MenuCategory;
import com.example.Centralthon.domain.store.entity.Store;

public record StoresByMenu(
        String storeName,
        double distance,
        int salePrice,
        int quantity
) {
    public static StoresByMenu from(Menu menu, double distance) {
        Store store = menu.getStore();
        return new StoresByMenu(
                store.getName(),
                distance,
                menu.getSalePrice(),
                menu.getQuantity()
        );
    }
}
