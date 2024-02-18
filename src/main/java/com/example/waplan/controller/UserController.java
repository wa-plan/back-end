package com.example.waplan.controller;

import com.example.waplan.apiPayload.ApiResponse;
import com.example.waplan.converter.UserConverter;
import com.example.waplan.domain.Member;
import com.example.waplan.dto.UserRequest;
import com.example.waplan.dto.UserResponse;
import com.example.waplan.repository.UserRepository;
import com.example.waplan.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //임시
    private final UserRepository userRepository;

    @GetMapping("/")
    public String mainP(Model model){
        String memberName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("memberId", memberName);
        return "index";
    }
    @GetMapping("/signup")
    public String join(){
        return "signup";
    }
    @PostMapping("/signup")
    public ApiResponse<UserResponse.JoinDTO> join(@RequestBody UserRequest.JoinDTO request){
        return ApiResponse.onSuccess(UserConverter.toJoinDTO(userService.join(request)));
    }

    @GetMapping("/login")
    public String loginP(){
        return "login";
    }

    @PatchMapping("/update/alarm/{userId}")
    public ApiResponse<UserResponse.AlarmUpdateDTO> alarmUpdate(@RequestBody @Valid UserRequest.AlarmUpdateDTO request){
        userService.alarmUpdate(request);
        return ApiResponse.onSuccess(UserConverter.toAlarmUpdateDTO(request));
    }

}
