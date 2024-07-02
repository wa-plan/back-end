package com.example.waplan.login.application;


import com.example.waplan.user.application.UserService;
import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    private LoginService loginService;

    private User user;

    @BeforeEach
    void setUp(){
        loginService = new LoginService(userRepository);


    }

}
