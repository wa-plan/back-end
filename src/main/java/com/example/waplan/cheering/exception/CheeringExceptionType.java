package com.example.waplan.cheering.exception;

import com.example.waplan.base.BaseExceptionType;
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