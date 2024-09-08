package com.example.dodakki.s3.exception;

import com.example.dodakki.base.BaseException;
import com.example.dodakki.base.BaseExceptionType;

public class S3Exception extends BaseException {

    private final S3ExceptionType exceptionType;

    public S3Exception(final S3ExceptionType exceptionType) {
        super(exceptionType.errorMessage());
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}