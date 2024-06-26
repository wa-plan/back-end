package com.example.waplan.goal.domain.repository;

import com.example.waplan.goal.domain.FirstGoal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstGoalRepository extends JpaRepository<FirstGoal, Long> {

}
