package com.example.waplan.goal.application;

import com.example.waplan.goal.application.dto.FirstGoalDto;
import com.example.waplan.goal.domain.FirstGoal;
import com.example.waplan.goal.domain.repository.FirstGoalRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {
    @Autowired
    private FirstGoalRepository firstGoalRepository;

    public FirstGoal addGoal(FirstGoalDto goalDto){
        FirstGoal goal = new FirstGoal();
        goal.setGoal(goalDto.getGoal());
        goal.setContent(goalDto.getContent());
        goal.setColor(goalDto.getColor());
        goal.setAchievementStatus(goalDto.getAchievementStatus());
        goal.setStreakCount(goalDto.getStreakCount());
        goal.setFavoriteStatus(goalDto.isFavoriteStatus());

        return firstGoalRepository.save(goal);
    }


    public List<FirstGoal> getAllFirstGoals() {
        return firstGoalRepository.findAll();
    }

    public Optional<FirstGoal> getFirstGoalById(Long id) {
        return firstGoalRepository.findById(id);
    }

    public void deleteFirstGoal(Long id){
        firstGoalRepository.deleteById(id);
    }
}
