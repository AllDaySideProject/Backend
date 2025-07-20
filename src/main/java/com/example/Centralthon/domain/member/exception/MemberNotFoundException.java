package com.example.Centralthon.domain.member.exception;

import com.example.Centralthon.global.exception.BaseException;

public class MemberNotFoundException extends BaseException {
    public MemberNotFoundException() {super(MemberErrorCode.MEMBER_NOT_FOUND);}
}
