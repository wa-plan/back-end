package com.example.dodakki.login.exception;

import com.example.dodakki.base.BaseException;

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
