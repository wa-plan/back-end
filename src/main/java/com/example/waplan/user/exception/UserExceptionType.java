package com.example.waplan.user.exception;

import com.example.waplan.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum UserExceptionType implements BaseExceptionType {

    NOT_FOUND_MEMBER(
        HttpStatus.NOT_FOUND,
        "해당 멤버는 존재하지 않습니다."
    ),


    OVER_LENGTH_DESCRIPTION(
        HttpStatus.BAD_REQUEST,
        "자기소개에 입력 가능한 글자 수를 초과했습니다."
    ),

    NOT_MATCHING_TOKEN_AND_LOGIN_MEMBER(
        HttpStatus.UNAUTHORIZED,
        "사용자가 일치하지 않습니다."
    );


    private final HttpStatus httpStatus;
    private final String errorMessage;

    UserExceptionType(final HttpStatus httpStatus, final String errorMessage) {
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
