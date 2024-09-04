package com.example.dodakki.goal.domain.repository;

import com.example.dodakki.goal.domain.GoalDate;
import com.example.dodakki.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface GoalDateRepository extends JpaRepository<GoalDate, Long> {
    Optional<GoalDate> findByUserAndDate(User user, LocalDate date);
    Optional<GoalDate> findByDate(LocalDate date);
}
