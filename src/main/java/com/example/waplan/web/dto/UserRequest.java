package com.example.waplan.web.dto;


import com.example.waplan.web.domain.enums.Alarm;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UserRequest {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class JoinDTO {

        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        private String userId;

        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        private String password;

        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        private String email;

        @NotBlank(message = "휴대전화 번호는 필수 입력 값입니다.")
        private String phoneNum;


    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class AlarmUpdateDTO{

        private Long id;

        private Alarm morningAlarm;

        private Alarm nightAlarm;
    }

}
