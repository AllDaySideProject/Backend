package com.example.Centralthon.domain.menu.web.dto;

import com.example.Centralthon.domain.menu.entity.Menu;

public record NearbyMenusRes(
        Long id,
        String name,
        String category,
        int salePrice,
        String storeName
) {
    public static NearbyMenusRes from(Menu m) {
        return new NearbyMenusRes(
                m.getId(),
                m.getName(),
                m.getCategory().getCategory(),
                m.getSalePrice(),
                m.getStore().getName()
        );
    }
}
