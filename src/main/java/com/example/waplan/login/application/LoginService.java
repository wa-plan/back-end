package com.example.waplan.login.application;

import com.example.waplan.login.application.dto.SignUpRequest;
import com.example.waplan.login.exception.AuthException;
import com.example.waplan.login.exception.AuthExceptionType;
import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginService {

    private final UserRepository userRepository;

    public Long registerUser(SignUpRequest signUpRequest) {
        if(userRepository.existsByUserId(signUpRequest.getUserId())){
            throw new AuthException(AuthExceptionType.DUPLICATED_ID);
        }
        User user = new User(signUpRequest.getUserId(), signUpRequest.getPassword(), signUpRequest.getEmail(), signUpRequest.getPhoneNum(), signUpRequest.getDescription(), signUpRequest.getNickname());

        User result = userRepository.save(user);
        return result.getId();
    }

}
