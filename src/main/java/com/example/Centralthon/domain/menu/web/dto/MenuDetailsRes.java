package com.example.Centralthon.domain.menu.web.dto;

import com.example.Centralthon.domain.menu.entity.Menu;
import com.example.Centralthon.domain.menu.entity.enums.MenuCategory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record MenuDetailsRes(
        long menuId,
        String name,
        MenuCategory category,
        int costPrice,
        int salePrice,
        int salePercent,
        String storeName
) {
    public static MenuDetailsRes from(Menu menu) {
        return new MenuDetailsRes(
                menu.getId(),
                menu.getName(),
                menu.getCategory(),
                menu.getCostPrice(),
                menu.getSalePrice(),
                calculateDiscount(menu.getCostPrice(), menu.getSalePrice()),
                menu.getStore().getName()
        );
    }

    private static int calculateDiscount(int cost, int sale) {
        return BigDecimal.valueOf(cost - sale)
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(cost), 0, RoundingMode.HALF_UP)
                .intValue();
    }
}
