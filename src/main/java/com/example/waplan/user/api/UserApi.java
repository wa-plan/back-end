package com.example.waplan.user.api;

import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.application.UserService;
import com.example.waplan.user.application.dto.MorningAlarmRequest;
import com.example.waplan.user.application.dto.NightAlarmRequest;
import com.example.waplan.user.application.dto.UpdatePasswordRequest;
import com.example.waplan.user.application.dto.UserResponse;
import com.example.waplan.user.application.dto.UserUpdateRequest;
import com.example.waplan.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
