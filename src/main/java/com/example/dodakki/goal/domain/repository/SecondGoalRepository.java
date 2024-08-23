package com.example.dodakki.goal.domain.repository;

import com.example.dodakki.goal.domain.SecondGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondGoalRepository extends JpaRepository<SecondGoal, Long> {

}
