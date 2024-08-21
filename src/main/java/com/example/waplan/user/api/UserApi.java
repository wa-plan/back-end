package com.example.waplan.user.api;

import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.application.UserService;
import com.example.waplan.user.application.dto.*;
import com.example.waplan.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApi {

    private final UserService userService;

    @PutMapping("/morning_alarm")
    public ResponseEntity<Void> updateMorningAlarm(
        @CurrentUser final User user,
        @RequestBody @Valid final MorningAlarmRequest morningAlarmRequest
    ) {
        userService.updateMorningAlarm(user, morningAlarmRequest);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/night_alarm")
    public ResponseEntity<Void> updateNightAlarm(
        @CurrentUser final User user,
        @RequestBody @Valid final NightAlarmRequest nightAlarmRequest
    ) {
        userService.updateNightAlarm(user, nightAlarmRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@CurrentUser User user) {
        return ResponseEntity.ok(UserResponse.of(user));
    }

    @PatchMapping("/me")
    public ResponseEntity<UserResponse> updateCurrentUser(@CurrentUser User user, @RequestBody @Valid UserUpdateRequest userRequest){
        userService.updateUser(user, userRequest);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCurrentUser(@CurrentUser User user){
        userService.deleteCurrentUser(user);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/me/password")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updatePassword(@CurrentUser User user, @Valid @RequestBody UpdatePasswordRequest updatePasswordRequest){
        userService.updatePassword(user, updatePasswordRequest);
    }

}
