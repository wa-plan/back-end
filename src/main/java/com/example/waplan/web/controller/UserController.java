package com.example.waplan.web.controller;

import static com.example.waplan.apiPayload.code.status.ErrorStatus.JOIN_ERROR;

import com.example.waplan.apiPayload.ApiResponse;
import com.example.waplan.apiPayload.code.status.ErrorStatus;
import com.example.waplan.apiPayload.exception.handler.UserHandler;
import com.example.waplan.web.converter.UserConverter;
import com.example.waplan.web.dto.UserRequest;
import com.example.waplan.web.dto.UserResponse;
import com.example.waplan.web.service.UserService;
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

    @PatchMapping("/update/alarm")
    public ApiResponse<UserResponse.AlarmUpdateDTO> alarmUpdate(@RequestBody @Valid UserRequest.AlarmUpdateDTO request){
        userService.alarmUpdate(request);
        return ApiResponse.onSuccess(UserConverter.toAlarmUpdateDTO(request));
    }

}
