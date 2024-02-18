package com.example.waplan.converter;

import com.example.waplan.domain.Member;
import com.example.waplan.dto.UserRequest;
import com.example.waplan.dto.UserResponse;
import com.example.waplan.dto.UserResponse.JoinDTO;
import java.time.LocalDateTime;

public class UserConverter {

    public static JoinDTO toJoinDTO(Member member){
        return UserResponse.JoinDTO.builder()
            .userId(member.getUserId())
            .password(member.getPassword())
            .email(member.getEmail())
            .phoneNum(member.getPhoneNum())
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
