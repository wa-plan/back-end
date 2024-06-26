package com.example.waplan.goal.domain.repository;

import com.example.waplan.goal.domain.FirstGoal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirstGoalRepository extends JpaRepository<FirstGoal, Long> {
    Boolean existsByUserId(String userId);
    Optional<FirstGoal> findByUserId(String userId);
    List<FirstGoal> findAllGoalsByUserId(String userId);
}
