package com.example.waplan.user;

import com.example.waplan.user.domain.AlarmType;
import com.example.waplan.user.domain.User;

public class UserFixture {
    public static User userFixture() {
        final User user = new User(1234L, "hyeonsoo", "1234", "hyeonsoo02@naver.com", "010-1234-5678", "zz", "hs", AlarmType.ON, AlarmType.ON);

        return user;
    }

}
