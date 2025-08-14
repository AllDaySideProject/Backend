package com.example.Centralthon.domain.order.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompleteOrderReq {
    @NotBlank(message = "픽업 코드는 필수 값입니다.")
    String code;
}
