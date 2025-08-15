package com.example.Centralthon.domain.menu.web.dto;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.menu.entity.enums.MenuCategory;

import java.math.BigDecimal;
import java.math.RoundingMode;

// 하위 공통 DTO
public record MenuItemRes(
        Long menuId,
        String name,
        MenuCategory category,    // 메뉴 카테고리 (예: 볶음)
        int costPrice,
        int salePrice,
        int salePercent,    // (costPrice - salePrice) * 100 / costPrice
        int quantity) {

    public static MenuItemRes from(Menu menu) {
        return new MenuItemRes(
                menu.getId(),
                menu.getName(),
                menu.getCategory(),
                menu.getCostPrice(),
                menu.getSalePrice(),
                calculateDiscount(menu.getCostPrice(), menu.getSalePrice()),
                menu.getQuantity()
        );
    }

    private static int calculateDiscount(int cost, int sale) {
        return BigDecimal.valueOf(cost - sale)
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(cost), 0, RoundingMode.HALF_UP)
                .intValue();
    }
}


