package com.example.Centralthon.global.response;

import com.example.Centralthon.global.response.code.BaseResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@RequiredArgsConstructor
public class BaseResponse {
    private final Boolean inSuccess;
    private final String code;
    private final String message;
    private final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    public static BaseResponse of(Boolean inSuccess, BaseResponseCode baseResponseCode) {
        return new BaseResponse(inSuccess, baseResponseCode.getCode(), baseResponseCode.getMessage());
    }

    public static BaseResponse of(Boolean inSuccess, BaseResponseCode baseResponseCode, String message){
        return new BaseResponse(inSuccess, baseResponseCode.getCode(), message);
    }

    public static BaseResponse of(Boolean inSuccess, String code, String message) {
        return new BaseResponse(inSuccess, code, message);
    }
}
