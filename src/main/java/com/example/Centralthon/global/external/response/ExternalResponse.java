package com.example.Centralthon.global.external.response;

public record ExternalResponse<T>(
        boolean isSuccess,
        int httpStatus,
        T data,
        String timeStamp
) {}
