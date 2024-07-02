package com.example.waplan.goal.domain.repository;

import com.example.waplan.goal.domain.FirstGoal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface FirstGoalRepository extends JpaRepository<FirstGoal, Long> {
    List<FirstGoal> findAllByUser_UserId(String userId);

    List<FirstGoal> findAllByAchievedAndUser_UserId(boolean achieved, String userId);

    @Query("SELECT fg.goal FROM FirstGoal fg WHERE fg.achieved = :achieved AND fg.user.userId = :userId")
    List<String> findAllTitlesByAchievedAndUser_UserId(@Param("achieved") boolean achieved, @Param("userId") String userId);
}
