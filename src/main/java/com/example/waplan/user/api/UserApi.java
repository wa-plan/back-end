package com.example.waplan.user.api;

import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.application.UserService;
import com.example.waplan.user.application.dto.MorningAlarmRequest;
import com.example.waplan.user.application.dto.UserResponse;
import com.example.waplan.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @PutMapping("/user/morning_alarm")
    public ResponseEntity<Void> updateDescription(
        final User user,
        @RequestBody final MorningAlarmRequest morningAlarmRequest
    ) {
        userService.updateMorningAlarm(user, morningAlarmRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@CurrentUser User user) {
        return ResponseEntity.ok(UserResponse.of(user));
    }

}
