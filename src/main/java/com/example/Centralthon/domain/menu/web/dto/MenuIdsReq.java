package com.example.Centralthon.domain.menu.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.util.List;

@Getter
public class MenuIdsReq {
    @NotEmpty(message = "menuIds 리스트가 비어있습니다.")
    private List<@NotNull(message = "menuId는 null일 수 없습니다.") Long> menuIds;
}
