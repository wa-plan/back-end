package com.example.waplan.goal.domain.repository;

import com.example.waplan.goal.domain.GoalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GoalDateRepository extends JpaRepository<GoalDate, Long> {

    Optional<GoalDate> findByDate(LocalDate date);
}
