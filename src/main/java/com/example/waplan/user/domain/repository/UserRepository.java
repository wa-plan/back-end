package com.example.waplan.user.domain.repository;

import com.example.waplan.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUserId(String userId);
    Optional<User> findByUserId(String userId);
}
