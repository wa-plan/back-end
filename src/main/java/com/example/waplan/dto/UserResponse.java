package com.example.waplan.dto;

import com.example.waplan.domain.enums.Alarm;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
public class UserResponse {

    @Getter
    @Builder
    public static class JoinDTO{
        @NotBlank
        private String userId;

        @NotBlank
        private String password;

        @NotBlank
        private String email;

        @NotBlank
        private String phoneNum;

        @NotBlank
        private LocalDateTime createdAt;

        @NotBlank
        private LocalDateTime updatedAt;
    }

    @Getter
    @Builder
    public static class AlarmUpdateDTO{

        private Alarm morningAlarm;

        private Alarm nightAlarm;
    }

}
