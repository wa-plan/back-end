package com.example.dodakki.login.exception;

import com.example.dodakki.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum AuthExceptionType implements BaseExceptionType {

    DUPLICATED_ID(
        HttpStatus.BAD_REQUEST,
        "이미 존재하는 아이디입니다."
    ),

    BAD_LOGIN(
        HttpStatus.BAD_REQUEST,
        "로그인 정보가 잘못되었습니다."
    ),

    NOT_FOUND_USER(
        HttpStatus.NOT_FOUND,
        "사용자를 찾을 수 없습니다."
    );

    private final HttpStatus httpStatus;
    private final String errorMessage;

    AuthExceptionType(final HttpStatus httpStatus, final String errorMessage) {
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
