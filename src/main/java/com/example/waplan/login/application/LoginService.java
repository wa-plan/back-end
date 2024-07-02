package com.example.waplan.login.application;

import com.example.waplan.login.application.dto.FindUserIdRequest;
import com.example.waplan.login.application.dto.ResetPasswordRequest;
import com.example.waplan.login.application.dto.SignUpRequest;
import com.example.waplan.login.exception.AuthException;
import com.example.waplan.login.exception.AuthExceptionType;
import com.example.waplan.user.domain.Role;
import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.repository.UserRepository;
import java.util.Objects;
import java.util.Optional;
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
        User user = new User(signUpRequest.getUserId(), signUpRequest.getPassword(), signUpRequest.getEmail(), signUpRequest.getPhoneNum(), signUpRequest.getDescription(), signUpRequest.getNickname(),
            Role.ROLE_USER);

        User result = userRepository.save(user);
        return result.getId();
    }
    public String findUserId(FindUserIdRequest findUserIdRequest){
        User user = userRepository.findByNicknameAndEmail(findUserIdRequest.getNickname(), findUserIdRequest.getEmail()).orElseThrow(() -> new AuthException(AuthExceptionType.NOT_FOUND_USER));
        return user.getUserId();
    }

    public String resetPassword(ResetPasswordRequest resetPasswordRequest) {
        User user = userRepository.findByUserId(resetPasswordRequest.getUserId()).orElseThrow(() -> new AuthException(AuthExceptionType.NOT_FOUND_USER));
        if(!Objects.equals(user.getEmail(), resetPasswordRequest.getEmail())){
            throw new AuthException(AuthExceptionType.NOT_FOUND_USER);
        }
        user.setPassword(createRandomPw());
        userRepository.save(user);
        return user.getPassword();
    }

    public String createRandomPw() {
        String[] StringSet = new String[]{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
        };

        String pwd = "";

        int randIndex = 0;
        for(int i=0;i<6;i++){
            randIndex = (int)(StringSet.length * Math.random());
            pwd += StringSet[randIndex];
        }
        return pwd;
    }

}
