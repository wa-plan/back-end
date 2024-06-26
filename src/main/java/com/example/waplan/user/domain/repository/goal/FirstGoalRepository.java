package com.example.waplan.user.domain.repository.goal;

import com.example.waplan.user.domain.User;
import com.example.waplan.user.domain.goal.FirstGoal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirstGoalRepository extends JpaRepository<FirstGoal, Long> {
    Boolean existsByUserId(String userId);
    Optional<FirstGoal> findByUserId(String userId);
    List<FirstGoal> findAllGoalsByUserId(String userId);
}
