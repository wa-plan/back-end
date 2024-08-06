package com.example.waplan.s3.exception;

import com.example.waplan.base.BaseException;
import com.example.waplan.base.BaseExceptionType;
import com.example.waplan.goal.exception.GoalDateExceptionType;

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