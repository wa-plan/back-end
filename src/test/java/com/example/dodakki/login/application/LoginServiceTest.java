package com.example.dodakki.login.application;


import com.example.dodakki.login.application.dto.SignUpRequest;
import com.example.dodakki.user.domain.User;
import com.example.dodakki.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.dodakki.user.UserFixture.userFixture;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    private LoginService loginService;

    private User user;


    @BeforeEach
    void setUp(){
        loginService = new LoginService(userRepository);
        user = userFixture();
    }


    @DisplayName("회원가입을 한다.")
    @Test
    void registerUser() {
        SignUpRequest signUpRequest = new SignUpRequest("hyeonsoo2002", "1234", "hs@naver.com", "01090701234", "zz", "aa");
        User user = userFixture();

        when(userRepository.existsByUserId(any())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        assertThat(loginService.registerUser(signUpRequest)).isEqualTo(user.getId());
    }

}
