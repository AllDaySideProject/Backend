package com.example.Centralthon.domain.menu.web.dto;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.menu.entity.enums.MenuCategory;

public record NearbyMenusRes(
        String name,
        MenuCategory category
) {
    public static NearbyMenusRes from(Menu m) {
        return new NearbyMenusRes(
                m.getName(),
                m.getCategory()
        );
    }
}
