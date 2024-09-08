package com.example.dodakki.cheering.exception;

import com.example.dodakki.base.BaseException;
import com.example.dodakki.base.BaseExceptionType;

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