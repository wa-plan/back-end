package com.example.waplan.user.application;

import static com.example.waplan.user.UserFixture.userFixture;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.waplan.login.application.dto.SignUpRequest;
import com.example.waplan.user.application.dto.MorningAlarmRequest;
import com.example.waplan.user.application.dto.NightAlarmRequest;
import com.example.waplan.user.domain.AlarmType;
import com.example.waplan.user.domain.Role;
import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
        user = userFixture();
    }

    @DisplayName("아침 알림이 정상적으로 업데이트된다.")
    @Test
    void updateMorningAlarm_success() {
        // given
        AlarmType expectAlarm = AlarmType.OFF;
        MorningAlarmRequest request = new MorningAlarmRequest(expectAlarm);
        // when
        userService.updateMorningAlarm(user, request);
        // then
        assertThat(user.getMorningAlarm()).isEqualTo(expectAlarm);
    }

    @DisplayName("저녁 알림이 정상적으로 업데이트 된다.")
    @Test
    void updateNightAlarm_success() {
        //given
        final User user = userRepository.save(userFixture());
        final AlarmType expectAlarm = AlarmType.OFF;
        final NightAlarmRequest request = new NightAlarmRequest(expectAlarm);

        //when
        userService.updateNightAlarm(user, request);
        final User actualUser = userRepository.findById(user.getId()).get();

        //then
        assertThat(actualUser.getNightAlarm()).isEqualTo(expectAlarm);
    }


}
