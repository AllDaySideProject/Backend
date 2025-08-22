package com.example.Centralthon.global.external.ai.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetTipReq {
    @NotNull(message = "메뉴 목록은 필수 값입니다.")
    @NotEmpty(message = "메뉴 목록은 비어있을 수 없습니다.")
    List<String> menus;
}
