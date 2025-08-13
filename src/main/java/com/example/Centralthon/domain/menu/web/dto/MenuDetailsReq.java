package com.example.Centralthon.domain.menu.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MenuDetailsReq {
    @NotNull(message = "menuId는 필수입니다.")
    private Long menuId;
    @Min(value=1, message="quantity는 1 이상이어야 합니다.")
    private int quantity;
}
