package com.example.waplan.goal.exception;

import com.example.waplan.base.BaseException;
import com.example.waplan.base.BaseExceptionType;


public class GoalException extends BaseException {

    private final GoalExceptionType exceptionType;

    public GoalException(final GoalExceptionType exceptionType) {
        super(exceptionType.errorMessage());
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}