package com.example.dodakki.cheering.exception;

import com.example.dodakki.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum CheeringExceptionType implements BaseExceptionType {

    NOT_FOUND_CHEERING(
        HttpStatus.NOT_FOUND,
        "해당 명언이 존재하지 않습니다."
    );

    private final HttpStatus httpStatus;
    private final String errorMessage;

    CheeringExceptionType(final HttpStatus httpStatus, final String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public String errorMessage() {
        return errorMessage;
    }
}