package com.example.waplan.goal.exception;

import com.example.waplan.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum MandalartExceptionType implements BaseExceptionType {


    NOT_FOUND_MANDALART(
        HttpStatus.NOT_FOUND,
        "해당 만다라트는 존재하지 않습니다."
    );


    private final HttpStatus httpStatus;
    private final String errorMessage;

    MandalartExceptionType(final HttpStatus httpStatus, final String errorMessage) {
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
