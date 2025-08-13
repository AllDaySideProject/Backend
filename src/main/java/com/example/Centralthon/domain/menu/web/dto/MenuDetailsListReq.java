package com.example.Centralthon.domain.menu.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import java.util.List;

@Getter
public class MenuDetailsListReq {
    @NotEmpty(message = "menus 리스트가 비어있습니다.")
    @Valid
    private List<MenuDetailsReq> menus;
}
