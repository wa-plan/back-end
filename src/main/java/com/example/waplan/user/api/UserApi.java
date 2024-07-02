package com.example.waplan.user.api;

import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.application.UserService;
import com.example.waplan.user.application.dto.MorningAlarmRequest;
import com.example.waplan.user.application.dto.NightAlarmRequest;
import com.example.waplan.user.application.dto.UserResponse;
import com.example.waplan.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApi {

    private final UserService userService;

    @PutMapping("/morning_alarm")
    public ResponseEntity<Void> updateMorningAlarm(
        @CurrentUser final User user,
        @RequestBody final MorningAlarmRequest morningAlarmRequest
    ) {
        userService.updateMorningAlarm(user, morningAlarmRequest);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/night_alarm")
    public ResponseEntity<Void> updateNightAlarm(
        @CurrentUser final User user,
        @RequestBody final NightAlarmRequest nightAlarmRequest
    ) {
        userService.updateNightAlarm(user, nightAlarmRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@CurrentUser User user) {
        return ResponseEntity.ok(UserResponse.of(user));
    }

}
