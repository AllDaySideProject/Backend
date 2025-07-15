package com.example.Centralthon.domain.member.exception;

import com.example.Centralthon.global.exception.BaseException;
import com.example.Centralthon.global.response.code.BaseResponseCode;

public class MemberAlreadyExistException extends BaseException {
    public MemberAlreadyExistException(MemberErrorCode errorCode) {
        super(errorCode);
    }
}
