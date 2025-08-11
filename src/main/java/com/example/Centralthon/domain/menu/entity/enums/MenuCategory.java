package com.example.Centralthon.domain.menu.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuCategory {
    SOUP("국"),
    BRAISED("조림"),
    SEASONED("나물"),
    STIR_FRY("볶음"),
    GRILLED("구이");

    private final String category;
}
