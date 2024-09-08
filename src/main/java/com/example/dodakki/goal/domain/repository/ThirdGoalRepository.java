package com.example.dodakki.goal.domain.repository;

import com.example.dodakki.goal.domain.ThirdGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdGoalRepository extends JpaRepository<ThirdGoal, Long> {

}
