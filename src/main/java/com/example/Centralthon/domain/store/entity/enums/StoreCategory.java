package com.example.Centralthon.domain.store.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreCategory {
    KOREAN("한식 전문점"),
    FUSION_SIDE_DISH("퓨전 반찬 전문점"),
    VEGAN_SIDE_DISH("채식/비건 반찬 전문점"),
    PREMIUM_SIDE_DISH("고급 반찬 전문점"),
    HOME_MADE("수제 반찬가게");

    private final String categoryName;
}
