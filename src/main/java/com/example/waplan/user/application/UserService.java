package com.example.waplan.user.application;

import com.example.waplan.user.application.dto.MorningAlarmRequest;
import com.example.waplan.user.domain.AlarmType;
import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.repository.UserRepository;
import com.example.waplan.user.exception.UserException;
import com.example.waplan.user.exception.UserExceptionType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void updateMorningAlarm(final User user, final MorningAlarmRequest morningAlarmRequest) {
        final AlarmType morningAlarm = morningAlarmRequest.getAlarm();

        final User persistUser = userRepository.findById(user.getId())
            .orElseThrow(() -> new UserException(UserExceptionType.NOT_FOUND_MEMBER));
        persistUser.updateMorningAlarm(morningAlarm);
    }
}
