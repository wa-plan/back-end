package com.example.waplan.controller;

import com.example.waplan.apiPayload.ApiResponse;
import com.example.waplan.converter.UserConverter;
import com.example.waplan.dto.UserRequest;
import com.example.waplan.dto.UserResponse;
import com.example.waplan.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @PostMapping("/signup")
    public ApiResponse<UserResponse.JoinDTO> join(@RequestBody @Valid UserRequest.JoinDTO request){
        userService.join(request);
        return ApiResponse.onSuccess(UserConverter.toJoinDTO(request));
    }

    @PatchMapping("/update/alarm/{userId}")
    public ApiResponse<UserResponse.AlarmUpdateDTO> alarmUpdate(@RequestBody @Valid UserRequest.AlarmUpdateDTO request){
        userService.alarmUpdate(request);
        return ApiResponse.onSuccess(UserConverter.toAlarmUpdateDTO(request));
    }

}
