package com.example.Centralthon.domain.order.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderReq {
    @NotNull(message = "메뉴 기본 키는 필수 값입니다.")
    private Long menuId;

    @NotNull(message = "수량은 필수 값입니다.")
    @Min(value = 1, message = "수량은 1 이상이어야 합니다.")
    private Integer count;
}
