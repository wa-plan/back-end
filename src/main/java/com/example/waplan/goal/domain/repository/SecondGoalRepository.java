package com.example.waplan.goal.domain.repository;

import com.example.waplan.goal.domain.SecondGoal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SecondGoalRepository extends JpaRepository<SecondGoal, Long> {
    List<SecondGoal> findByFirstGoalId(Long firstGoalId);
}
