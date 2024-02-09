package com.example.waplan.service;

import com.example.waplan.apiPayload.code.status.ErrorStatus;
import com.example.waplan.apiPayload.exception.handler.UserHandler;
import com.example.waplan.domain.Member;
import com.example.waplan.dto.UserRequest.AlarmUpdateDTO;
import com.example.waplan.dto.UserRequest.JoinDTO;
import com.example.waplan.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long join(JoinDTO request){
        Member user = Member.builder()
            .userId(request.getUserId())
            .password(request.getPassword())
            .email(request.getEmail())
            .phoneNum(request.getPhoneNum())
            .created_at(LocalDateTime.now())
            .updated_at(LocalDateTime.now())
            .build();
        //중복 회원 검증
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(Member user){
        List<Member> findUsers = userRepository.findUserEntityByUserId(user.getUserId());
        if(!findUsers.isEmpty()){
            throw new UserHandler(ErrorStatus.JOIN_ERROR);
        }
    }

    public void alarmUpdate(AlarmUpdateDTO request){
        Member user = userRepository.findById(request.getId()).orElseThrow();
        user.setMorningAlarm(request.getMorningAlarm());
        user.setNightAlarm(request.getNightAlarm());
        userRepository.save(user);
    }
}
