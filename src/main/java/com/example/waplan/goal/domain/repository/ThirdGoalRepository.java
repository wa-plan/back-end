package com.example.waplan.goal.domain.repository;

import com.example.waplan.goal.domain.ThirdGoal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdGoalRepository extends JpaRepository<ThirdGoal, Long> {
    List<ThirdGoal> findBySecondGoalId(Long secondGoalId);
}
