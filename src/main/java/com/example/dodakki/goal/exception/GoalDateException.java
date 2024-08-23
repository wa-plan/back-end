package com.example.dodakki.goal.exception;

import com.example.dodakki.base.BaseException;
import com.example.dodakki.base.BaseExceptionType;

public class GoalDateException extends BaseException {

    private final GoalDateExceptionType exceptionType;

    public GoalDateException(final GoalDateExceptionType exceptionType) {
        super(exceptionType.errorMessage());
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}