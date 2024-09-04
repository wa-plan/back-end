package com.example.dodakki.goal.exception;

import com.example.dodakki.base.BaseException;
import com.example.dodakki.base.BaseExceptionType;

public class GoalDateMapException extends BaseException {

    private final GoalDateMapExceptionType exceptionType;

    public GoalDateMapException(final GoalDateMapExceptionType exceptionType) {
        super(exceptionType.errorMessage());
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}