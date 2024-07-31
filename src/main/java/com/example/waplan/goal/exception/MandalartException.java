package com.example.waplan.goal.exception;

import com.example.waplan.base.BaseException;
import com.example.waplan.base.BaseExceptionType;
import com.example.waplan.user.exception.UserExceptionType;

public class MandalartException extends BaseException {

    private final MandalartExceptionType exceptionType;

    public MandalartException(final MandalartExceptionType exceptionType) {
        super(exceptionType.errorMessage());
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
