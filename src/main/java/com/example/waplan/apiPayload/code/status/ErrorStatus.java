package com.example.waplan.apiPayload.code.status;

import com.example.waplan.apiPayload.code.BaseErrorCode;
import com.example.waplan.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),
    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),
    INVALID_USER(HttpStatus.BAD_REQUEST, "MEMBER4003", "등록되지 않은 사용자입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "MEMBER4004", "비밀번호가 틀립니다."),
    LOGIN_ERROR(HttpStatus.BAD_REQUEST, "MEMBER4005", "로그인 중 오류가 발생했습니다."),
    JOIN_ERROR(HttpStatus.BAD_REQUEST, "MEMBER4006", "회원가입 중 오류가 발생했습니다."),
    NICKNAME_CHANGE_ERROR(HttpStatus.BAD_REQUEST, "MEMBER4007", "닉네임 변경 중 오류가 발생했습니다."),
    PROFILE_CHANGE_ERROR(HttpStatus.BAD_REQUEST, "MEMBER4008", "프로필 변경 중 오류가 발생했습니다."),
    PASSWORD_CHANGE_FAILURE(HttpStatus.BAD_REQUEST, "MEMBER4009", "비밀번호 변경에 실패했습니다."),
    NICKNAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "MEMBER4010", "이미 존재하는 닉네임입니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "MEMBER4011", "이미 존재하는 이메일입니다."),

    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4012", "게시글이 없습니다.");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
            .message(message)
            .code(code)
            .isSuccess(false)
            .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
            .message(message)
            .code(code)
            .isSuccess(false)
            .httpStatus(httpStatus)
            .build();
    }
}