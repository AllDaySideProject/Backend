package com.example.Centralthon.domain.route.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
public class RouteReq{
    @NotNull(message = "userLat을 입력해주세요.")
    private Double userLat;

    @NotNull(message = "userLng을 입력해주세요.")
    private Double userLng;

    @NotEmpty(message = "storeIds 리스트가 비어있습니다.")
    @Size(max = 5, message = "경유지는 최대 5개까지만 지정할 수 있습니다.")
    @UniqueElements(message = "storeIds에 중복 값이 포함되어 있습니다.")
    private List<Long> storeIds;
}
