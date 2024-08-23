package com.example.dodakki.goal.domain.repository;

import com.example.dodakki.goal.domain.GoalDate;
import com.example.dodakki.goal.domain.GoalDateMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalDateMapRepository extends JpaRepository<GoalDateMap, Long> {
    List<GoalDateMap> findByGoalDate(GoalDate goalDate);
}
