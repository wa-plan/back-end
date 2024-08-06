package com.example.waplan.s3.exception;

import com.example.waplan.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum S3ExceptionType implements BaseExceptionType {


    EMPTY_FILE_EXCEPTION(
            HttpStatus.NOT_FOUND,
            "해당 파일은 존재하지 않습니다."
    ),
    IO_EXCEPTION_ON_IMAGE_UPLOAD(
            HttpStatus.CONFLICT,
            "이미지 업로드에 실패했습니다."
    ),
    NO_FILE_EXTENTION(
            HttpStatus.NOT_FOUND,
            "파일 확장명이 존재하지 않습니다."
    ),
    INVALID_FILE_EXTENTION(
            HttpStatus.NOT_ACCEPTABLE,
            "파일 확장명이 허용되지 않습니다."
    ),
    PUT_OBJECT_EXCEPTION(
            HttpStatus.NOT_EXTENDED,
            "이미지를 S3 파일로 변환에 실패했습니다."
    ),
    IO_EXCEPTION_ON_IMAGE_DELETE(
            HttpStatus.CONFLICT,
            "이미지 삭제에 실패했습니다."
    );

    private final HttpStatus httpStatus;
    private final String errorMessage;

    S3ExceptionType(final HttpStatus httpStatus, final String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public String errorMessage() {
        return errorMessage;
    }
}