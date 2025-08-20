package com.example.Centralthon.global.external.exception;

import com.example.Centralthon.global.exception.BaseException;

public class AiCommunicationFailedException extends BaseException {
    public AiCommunicationFailedException() {super(AiErrorCode.AI_COMMUNICATION_FAILED);}
}