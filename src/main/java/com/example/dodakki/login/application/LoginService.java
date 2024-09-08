package com.example.dodakki.login.application;

import com.example.dodakki.login.application.dto.FindUserIdRequest;
import com.example.dodakki.login.application.dto.SignUpRequest;
import com.example.dodakki.login.exception.AuthException;
import com.example.dodakki.login.exception.AuthExceptionType;
import com.example.dodakki.user.domain.Role;
import com.example.dodakki.user.domain.User;
import com.example.dodakki.user.domain.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${image.baseProfile}")
    private String defaultProfile;

    public Long registerUser(SignUpRequest signUpRequest) {
        if(userRepository.existsByUserId(signUpRequest.getUserId())){
            throw new AuthException(AuthExceptionType.DUPLICATED_ID);
        }
        String imgUrl = defaultProfile;
        User user = new User(signUpRequest.getUserId(), signUpRequest.getPassword(), signUpRequest.getEmail(), signUpRequest.getPhoneNum(), "", "기쁜 도민호씨",
            Role.ROLE_USER, imgUrl);

        User result = userRepository.save(user);
        return result.getId();
    }
    public String findUserId(FindUserIdRequest findUserIdRequest){
        User user = userRepository.findByPhoneNumAndEmail(findUserIdRequest.getPhoneNum(), findUserIdRequest.getEmail()).orElseThrow(() -> new AuthException(AuthExceptionType.NOT_FOUND_USER));
        return user.getUserId();
    }

    public void setTempPassword(String email, String authNum){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AuthException(AuthExceptionType.NOT_FOUND_USER));
        user.updatePassword(passwordEncoder.encode(authNum));
        userRepository.save(user);
    }

}
