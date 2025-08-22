package com.example.Centralthon.global.external.exception;

import com.example.Centralthon.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AiErrorCode implements BaseResponseCode {
    AI_COMMUNICATION_FAILED("AI_500", 500, "AI 서버 호출에 실패했습니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
