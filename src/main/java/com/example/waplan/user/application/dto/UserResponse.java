package com.example.waplan.user.application.dto;

import com.example.waplan.user.domain.AlarmType;
import com.example.waplan.user.domain.Role;
import com.example.waplan.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserResponse {

    private Long id;

    private String userId;

    private String password;

    private String email;

    private String phoneNum;

    private String description;

    private Role role;

    private AlarmType morningAlarm;

    private AlarmType nightAlarm;

    private String nickname;

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getUserId(), user.getPassword(), user.getEmail(), user.getPhoneNum(), user.getDescription(), user.getRole(), user.getMorningAlarm(), user.getNightAlarm(), user.getNickname());
    }
}
