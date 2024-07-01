package com.example.waplan.goal.application;

import com.example.waplan.goal.application.dto.FirstGoalDto;
import com.example.waplan.goal.domain.AchievementStatus;
import com.example.waplan.goal.domain.FirstGoal;
import com.example.waplan.goal.domain.repository.FirstGoalRepository;
import com.example.waplan.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {
    @Autowired
    private FirstGoalRepository firstGoalRepository;

    public FirstGoal addGoal(FirstGoalDto goalDto, User user){
        FirstGoal goal = new FirstGoal();
        goal.setGoal(goalDto.getGoal());
        goal.setContent(goalDto.getContent());
        goal.setColor(goalDto.getColor());
        goal.setAchievementStatus(AchievementStatus.NOT_STARTED);
        goal.setStreakCount(goalDto.getStreakCount());
        goal.setFavoriteStatus(goalDto.isFavoriteStatus());
        goal.setUser(user);

        return firstGoalRepository.save(goal);
    }

    public Optional<FirstGoal> getFirstGoalById(Long id) {
        return firstGoalRepository.findById(id);
    }

    public void deleteFirstGoal(Long id){
        firstGoalRepository.deleteById(id);
    }

    public List<FirstGoal> getAllGoals(String userId) {
        return firstGoalRepository.findAllByUser_UserId(userId);
    }
}
