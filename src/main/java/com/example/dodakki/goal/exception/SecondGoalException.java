package com.example.dodakki.goal.exception;

import com.example.dodakki.base.BaseException;
import com.example.dodakki.base.BaseExceptionType;

public class SecondGoalException extends BaseException {

    private final SecondGoalExceptionType exceptionType;

    public SecondGoalException(final SecondGoalExceptionType exceptionType) {
        super(exceptionType.errorMessage());
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}

