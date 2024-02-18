package com.example.waplan.service;

import com.example.waplan.apiPayload.code.status.ErrorStatus;
import com.example.waplan.apiPayload.exception.handler.UserHandler;
import com.example.waplan.domain.Member;
import com.example.waplan.dto.UserRequest.AlarmUpdateDTO;
import com.example.waplan.dto.UserRequest.JoinDTO;
import com.example.waplan.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Member join(JoinDTO request){
        Member member;
        if(!Objects.equals(request.getUserId(), "admin")) {
            member = Member.builder()
                .userId(request.getUserId())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phoneNum(request.getPhoneNum())
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .role("ROLE_USER")
                .build();
        }
        else{
            member = Member.builder()
                .userId(request.getUserId())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phoneNum(request.getPhoneNum())
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .role("ROLE_ADMIN")
                .build();
        }
        //중복 회원 검증
        validateDuplicateUser(member);
        return userRepository.save(member);
    }

    private void validateDuplicateUser(Member member){
        if(userRepository.existsByUserId(member.getUserId())){
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
