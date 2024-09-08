package com.example.dodakki.user.exception;

import com.example.dodakki.base.BaseException;
import com.example.dodakki.base.BaseExceptionType;

public class UserException extends BaseException {

    private final UserExceptionType exceptionType;

    public UserException(final UserExceptionType exceptionType) {
        super(exceptionType.errorMessage());
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
