package com.example.waplan.login.exception;

import com.example.waplan.base.BaseException;

public class AuthException extends BaseException {

    private final AuthExceptionType exceptionType;

    public AuthException(final AuthExceptionType exceptionType) {
        super(exceptionType.errorMessage());
        this.exceptionType = exceptionType;
    }

    @Override
    public AuthExceptionType exceptionType() {
        return exceptionType;
    }
}
