package com.example.waplan.login.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ResetPasswordRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String email;
}
