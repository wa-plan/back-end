package com.example.waplan.goal.domain.repository;

import com.example.waplan.goal.domain.GoalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalDateRepository extends JpaRepository<GoalDate, Long> {

}
