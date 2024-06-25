package com.example.waplan.user.application;

import static com.example.waplan.user.UserFixture.userFixture;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.waplan.user.application.dto.MorningAlarmRequest;
import com.example.waplan.user.domain.AlarmType;
import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest {

    private UserRepository userRepository;

    private UserService userService;

    @Nested
    @DisplayName("알람를 업데이트한다.")
    class UpdateAlarm {

        @DisplayName("정상적으로 업데이트된다.")
        void updateMorningAlarm_success() {
            // given
            final User user = userRepository.save(userFixture());
            final AlarmType expectAlarm = AlarmType.OFF;
            final MorningAlarmRequest request = new MorningAlarmRequest(expectAlarm);
            // when
            userService.updateMorningAlarm(user, request);
            final User actualUser = userRepository.findById(user.getId()).get();

            // then
            assertThat(actualUser.getMorningAlarm()).isEqualTo(expectAlarm);
        }
    }
}
