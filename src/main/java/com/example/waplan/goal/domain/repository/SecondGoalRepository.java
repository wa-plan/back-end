package com.example.waplan.goal.domain.repository;

import com.example.waplan.goal.domain.SecondGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondGoalRepository extends JpaRepository<SecondGoal, Long> {

}
