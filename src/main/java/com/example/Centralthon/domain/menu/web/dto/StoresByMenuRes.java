package com.example.Centralthon.domain.menu.web.dto;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.menu.entity.enums.MenuCategory;
import com.example.Centralthon.domain.store.entity.Store;

public record StoresByMenuRes(
        String storeName,
        double distance,
        int salePrice,
        int quantity
) {
    public static StoresByMenuRes from(Menu menu, double distance) {
        Store store = menu.getStore();
        return new StoresByMenuRes(
                store.getName(),
                distance,
                menu.getSalePrice(),
                menu.getQuantity()
        );
    }
}
