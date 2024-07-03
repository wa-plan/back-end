package com.example.waplan.user.application;

import com.example.waplan.user.application.dto.MorningAlarmRequest;
import com.example.waplan.user.application.dto.NightAlarmRequest;
import com.example.waplan.user.application.dto.UpdatePasswordRequest;
import com.example.waplan.user.application.dto.UserUpdateRequest;
import com.example.waplan.user.domain.AlarmType;
import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.repository.UserRepository;
import com.example.waplan.user.exception.UserException;
import com.example.waplan.user.exception.UserExceptionType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void updateMorningAlarm(final User user, final MorningAlarmRequest morningAlarmRequest) {
        final AlarmType morningAlarm = morningAlarmRequest.getAlarm();
        final User persistUser = userRepository.findById(user.getId())
            .orElseThrow(() -> new UserException(UserExceptionType.NOT_FOUND_MEMBER));
        persistUser.updateMorningAlarm(morningAlarm);
    }

    public void updateNightAlarm(final User user, final NightAlarmRequest nightAlarmRequest) {
        final AlarmType nightAlarm = nightAlarmRequest.getAlarm();

        final User persistUser = userRepository.findById(user.getId())
            .orElseThrow(() -> new UserException(UserExceptionType.NOT_FOUND_MEMBER));
        persistUser.updateNightAlarm(nightAlarm);
    }

    public void deleteCurrentUser(final User user) {

        final User persistUser = userRepository.findById(user.getId())
            .orElseThrow(() -> new UserException(UserExceptionType.NOT_FOUND_MEMBER));
        userRepository.deleteById(persistUser.getId());
    }

    public void updateUser(User user, UserUpdateRequest userRequest){
        user.setNickname(userRequest.getNickname());
        userRepository.save(user);
    }

    public void updatePassword(final User user, final UpdatePasswordRequest updatePasswordRequest){
        User persistUser = userRepository.findById(user.getId())
            .orElseThrow(() -> new UserException(UserExceptionType.NOT_FOUND_MEMBER));
        if(!passwordEncoder.matches(updatePasswordRequest.getCurrentPassword(), persistUser.getPassword())){
            throw new UserException(UserExceptionType.INVALID_PASSWORD);
        }
        persistUser.updatePassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
    }
}
