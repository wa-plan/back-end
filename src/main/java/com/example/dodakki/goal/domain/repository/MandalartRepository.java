package com.example.dodakki.goal.domain.repository;

import com.example.dodakki.goal.domain.Mandalart;
import com.example.dodakki.goal.domain.Status;
import com.example.dodakki.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MandalartRepository extends JpaRepository<Mandalart, Long> {

    Optional<Mandalart> findByUserIdAndName(Long userId, String name);
}
