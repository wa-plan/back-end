package com.example.waplan.user.api;

import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.application.UserProfileService;
import com.example.waplan.user.application.dto.UserProfileDto;
import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserProfileApi {
    @Autowired
    private UserProfileService userProfileService;

    @PostMapping
    public ResponseEntity<UserProfile> saveUserProfile(@RequestBody UserProfileDto userProfileDto, @CurrentUser User user) {
        UserProfile userProfile = userProfileService.saveUserProfile(userProfileDto, user);
        return ResponseEntity.ok(userProfile);
    }

    @GetMapping
    public ResponseEntity<UserProfile> getUserProfile(@CurrentUser User user) {
        UserProfile userProfile = userProfileService.getUserProfile(user.getId());
        if (userProfile != null) {
            return ResponseEntity.ok(userProfile);
        }
        return ResponseEntity.notFound().build();
    }

}
