package com.example.dodakki.goal.exception;

import com.example.dodakki.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum SecondGoalExceptionType implements BaseExceptionType {


    NOT_FOUND_SECONDGOAL(
            HttpStatus.NOT_FOUND,
            "해당 제 2목표는 존재하지 않습니다."
    );


    private final HttpStatus httpStatus;
    private final String errorMessage;

    SecondGoalExceptionType(final HttpStatus httpStatus, final String errorMessage) {
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
