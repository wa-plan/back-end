package com.example.dodakki.user.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserUpdateRequest {

    @NotBlank
    private String nickname;

    private String profile;

    private String description;

}
