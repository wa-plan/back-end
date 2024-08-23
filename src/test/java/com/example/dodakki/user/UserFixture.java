package com.example.dodakki.user;

import com.example.dodakki.user.domain.AlarmType;
import com.example.dodakki.user.domain.Role;
import com.example.dodakki.user.domain.User;

public class UserFixture {
    public static User userFixture() {
        User user = new User(1234L, "hyeonsoo", "1234", "hyeonsoo02@naver.com", "01012345678", "zz", "hs", Role.ROLE_USER, AlarmType.ON, AlarmType.ON);

        return user;
    }

}
