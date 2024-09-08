package com.example.dodakki.goal.exception;

import com.example.dodakki.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum GoalDateMapExceptionType implements BaseExceptionType {


    NOT_FOUND_GOAL_DATE_MAP(
            HttpStatus.NOT_FOUND,
            "해당 매핑은 존재하지 않습니다."
    );


    private final HttpStatus httpStatus;
    private final String errorMessage;

    GoalDateMapExceptionType(final HttpStatus httpStatus, final String errorMessage) {
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
