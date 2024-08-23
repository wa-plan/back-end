package com.example.dodakki.goal.exception;

import com.example.dodakki.base.BaseException;
import com.example.dodakki.base.BaseExceptionType;

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
