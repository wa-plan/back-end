package com.example.waplan.goal.domain.repository;

import com.example.waplan.goal.domain.GoalDateMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalDateMapRepository extends JpaRepository<GoalDateMap, Long> {

}
