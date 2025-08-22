package com.example.Centralthon.domain.menu.web.dto;

import com.example.Centralthon.domain.menu.entity.enums.Concept;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRecommendedMenusReq {
    @DecimalMin(value = "-90.0", message = "위도는 -90 이상이어야 합니다.")
    @DecimalMax(value = "90.0", message = "위도는 90 이하이어야 합니다.")
    @NotNull(message = "위도는 필수값입니다.")
    double latitude;

    @DecimalMin(value = "-180.0", message = "경도는 -180 이상이어야 합니다.")
    @DecimalMax(value = "180.0", message = "경도는 180 이하이어야 합니다.")
    @NotNull(message = "경도는 필수값입니다.")
    double longitude;

    @NotNull(message = "컨셉은 필수 값입니다.")
    Concept concept;

    @Min(value = 1, message = "최소 1개 이상 조회해야 합니다.")
    @Max(value = 50, message = "최대 50개까지 조회할 수 있습니다.")
    @NotNull(message = "숫자는 필수 값입니다.")
    int count; // 몇 개 조회할지
}
