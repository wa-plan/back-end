package com.example.waplan.web.converter;

import com.example.waplan.web.domain.User_;
import com.example.waplan.web.dto.UserRequest;
import com.example.waplan.web.dto.UserResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserConverter {

    public static UserResponse.JoinDTO toJoinDTO(UserRequest.JoinDTO user){
        return UserResponse.JoinDTO.builder()
            .userId(user.getUserId())
            .password(user.getPassword())
            .email(user.getEmail())
            .phoneNum(user.getPhoneNum())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }
    public static UserResponse.AlarmUpdateDTO toAlarmUpdateDTO(UserRequest.AlarmUpdateDTO user){
        return UserResponse.AlarmUpdateDTO.builder()
            .morningAlarm(user.getMorningAlarm())
            .nightAlarm(user.getNightAlarm())
            .build();
    }
}
