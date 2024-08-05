package com.example.waplan.login.application;

import com.example.waplan.login.application.dto.FindUserIdRequest;
import com.example.waplan.login.application.dto.SignUpRequest;
import com.example.waplan.login.exception.AuthException;
import com.example.waplan.login.exception.AuthExceptionType;
import com.example.waplan.user.domain.Role;
import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginService {

    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;

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
        User user = userRepository.findByPhoneNumAndEmail(findUserIdRequest.getPhoneNum(), findUserIdRequest.getEmail()).orElseThrow(() -> new AuthException(AuthExceptionType.NOT_FOUND_USER));
        return user.getUserId();
    }

//    public String resetPassword(ResetPasswordRequest resetPasswordRequest) {
//        User user = userRepository.findByUserId(resetPasswordRequest.getUserId()).orElseThrow(() -> new AuthException(AuthExceptionType.NOT_FOUND_USER));
//        if(!Objects.equals(user.getEmail(), resetPasswordRequest.getEmail())){
//            throw new AuthException(AuthExceptionType.NOT_FOUND_USER);
//        }
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        return user.getPassword();
//    }

    public void setTempPassword(String email, String authNum){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AuthException(AuthExceptionType.NOT_FOUND_USER));
        user.updatePassword(passwordEncoder.encode(authNum));
        userRepository.save(user);
    }

}
