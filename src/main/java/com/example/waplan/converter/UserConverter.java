package com.example.waplan.converter;

import com.example.waplan.dto.UserRequest;
import com.example.waplan.dto.UserResponse;
import com.example.waplan.dto.UserResponse.JoinDTO;
import java.time.LocalDateTime;

public class UserConverter {

    public static JoinDTO toJoinDTO(UserRequest.JoinDTO user){
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
