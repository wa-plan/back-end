package com.example.waplan.service;

import com.example.waplan.domain.Member;
import com.example.waplan.dto.CustomUserDetails;
import com.example.waplan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = userRepository.findByUserId(userId);
        if(member != null){
            return new CustomUserDetails(member);
        }
        return null;
    }
}
