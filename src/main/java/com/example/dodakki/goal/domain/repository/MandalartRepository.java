package com.example.dodakki.goal.domain.repository;

import com.example.dodakki.goal.domain.Mandalart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MandalartRepository extends JpaRepository<Mandalart, Long> {

    Optional<Mandalart> findByUserIdAndName(Long userId, String name);
}
