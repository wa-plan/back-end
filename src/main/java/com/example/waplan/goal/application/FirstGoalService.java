package com.example.waplan.goal.application;

import com.example.waplan.goal.application.dto.FirstGoalAddDto;
import com.example.waplan.goal.application.dto.FirstGoalDto;
import com.example.waplan.goal.domain.AchievementStatus;
import com.example.waplan.goal.domain.FirstGoal;
import com.example.waplan.goal.domain.GoalDate;
import com.example.waplan.goal.domain.repository.FirstGoalRepository;
import com.example.waplan.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstGoalService {
    @Autowired
    private FirstGoalRepository firstGoalRepository;

    public FirstGoal addGoal(FirstGoalAddDto goalDto, User user){
        FirstGoal goal = new FirstGoal();
        goal.setGoal(goalDto.getGoal());
        goal.setContent(goalDto.getContent());
        goal.setColor(goalDto.getColor());
        goal.setPicture(goalDto.getPicture());
        goal.setAchieved(false);
        goal.setStreakCount(0);
        goal.setFavoriteStatus(false);
        goal.setUser(user);

        GoalDate goalDate = new GoalDate();
        goalDate.setDates(List.of(goalDto.getDate()));
        goal.setGoalDate(goalDate);

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
