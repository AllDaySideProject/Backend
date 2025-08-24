package com.example.Centralthon.global.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import java.lang.annotation.*;

@Constraint(validatedBy = {})
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@DecimalMin(value = "-180.0")
@DecimalMax(value = "180.0")
public @interface Longitude {
    String message() default "유효하지 않은 경도입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
