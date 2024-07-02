package com.example.waplan.user.application;

import com.example.waplan.security.CurrentUser;
import com.example.waplan.user.application.dto.UserProfileDto;
import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.UserProfile;
import com.example.waplan.user.domain.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile saveUserProfile(UserProfileDto userProfileDto, User user){
        UserProfile userProfile = new UserProfile();
        userProfile.setNickname(userProfileDto.getNickname());
        userProfile.setDetail(userProfileDto.getDetail());
        userProfile.setPicture(userProfileDto.getPicture());
        userProfile.setUser(user);
        return userProfileRepository.save(userProfile);
    }

    public UserProfile getUserProfile(Long userId) {
        return userProfileRepository.findByUserId(userId);
    }
}
