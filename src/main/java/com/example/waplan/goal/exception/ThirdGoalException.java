package com.example.waplan.goal.exception;

import com.example.waplan.base.BaseException;
import com.example.waplan.base.BaseExceptionType;

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