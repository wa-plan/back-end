package com.example.waplan.goal.exception;

import com.example.waplan.base.BaseException;
import com.example.waplan.base.BaseExceptionType;

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

