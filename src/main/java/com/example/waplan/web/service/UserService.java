package com.example.waplan.web.service;

import com.example.waplan.apiPayload.code.status.ErrorStatus;
import com.example.waplan.apiPayload.exception.handler.UserHandler;
import com.example.waplan.web.domain.User_;
import com.example.waplan.web.dto.UserRequest;
import com.example.waplan.web.dto.UserResponse;
import com.example.waplan.web.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long join(UserRequest.JoinDTO request){
        User_ user = User_.builder()
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

    private void validateDuplicateUser(User_ user){
        List<User_> findUsers = userRepository.findUserEntityByUserId(user.getUserId());
        if(!findUsers.isEmpty()){
            throw new UserHandler(ErrorStatus.JOIN_ERROR);
        }
    }

    public void alarmUpdate(UserRequest.AlarmUpdateDTO request){
        User_ user = userRepository.findById(request.getId()).orElseThrow();
        user.setMorningAlarm(request.getMorningAlarm());
        user.setNightAlarm(request.getNightAlarm());
        userRepository.save(user);
    }
}
