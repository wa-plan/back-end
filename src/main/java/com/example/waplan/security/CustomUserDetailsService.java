package com.example.waplan.security;

import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다. id: " + userId);
        }
        User result = user.get();
        return UserPrincipal.create(result);
    }
}
