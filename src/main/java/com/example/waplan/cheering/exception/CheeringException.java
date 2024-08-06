package com.example.waplan.cheering.exception;

import com.example.waplan.base.BaseException;
import com.example.waplan.base.BaseExceptionType;

public class CheeringException extends BaseException {

    private final CheeringExceptionType exceptionType;

    public CheeringException(final CheeringExceptionType exceptionType) {
        super(exceptionType.errorMessage());
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}