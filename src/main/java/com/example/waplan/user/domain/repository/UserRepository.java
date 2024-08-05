package com.example.waplan.user.domain.repository;

import com.example.waplan.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUserId(String userId);
    Optional<User> findByUserId(String userId);
    Optional<User> findByPhoneNumAndEmail(String phoneNum, String email);
    Optional<User> findByEmail(String email);
}
