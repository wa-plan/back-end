package com.example.dodakki.goal.exception;

import com.example.dodakki.base.BaseException;
import com.example.dodakki.base.BaseExceptionType;

public class ThirdGoalException extends BaseException {

    private final ThirdGoalExceptionType exceptionType;

    public ThirdGoalException(final ThirdGoalExceptionType exceptionType) {
        super(exceptionType.errorMessage());
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}