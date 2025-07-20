package com.example.Centralthon.domain.member.exception;

import com.example.Centralthon.global.exception.BaseException;

public class InvalidCredentialsException extends BaseException {
    public InvalidCredentialsException( ) {super(MemberErrorCode.INVALID_CREDENTIALS);}
}
