package com.example.waplan.goal.application;

import com.example.waplan.goal.application.dto.ThirdGoalAchievementDto;
import com.example.waplan.goal.application.dto.ThirdGoalDto;
import com.example.waplan.goal.application.dto.ThirdGoalNewTitleDto;
import com.example.waplan.goal.domain.AchievementLevel;
import com.example.waplan.goal.domain.GoalDate;
import com.example.waplan.goal.domain.ThirdGoal;
import com.example.waplan.goal.domain.repository.ThirdGoalRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdGoalService {
    @Autowired
    private ThirdGoalRepository thirdGoalRepository;

    public ThirdGoal updateThirdGoal(ThirdGoalDto goalDto){
        ThirdGoal goal = thirdGoalRepository.findById(goalDto.getId()).orElseThrow();
        goal.setTitle(goalDto.getTitle());
        goal.setColor(goalDto.getColor());
        goal.setAchievementLevel(goalDto.getAchievementLevel());
        goal.setGoalDates(goalDto.getGoalDates());

        String newTitle = goalDto.getNewTitle();
        if(newTitle != null){
            goal.setNewTitle(newTitle);
        }

        return goal;
    }

    public void deleteThirdGoal(Long id){
        thirdGoalRepository.deleteById(id);
    }

    public Optional<ThirdGoal> getThirdGoalById(Long id){
        return thirdGoalRepository.findById(id);
    }

    public ThirdGoal addNewTitle(Long id, String newTitle){
        ThirdGoal goal = thirdGoalRepository.findById(id).orElseThrow();
        goal.setNewTitle(newTitle);
        return thirdGoalRepository.save(goal);
    }

    public Optional<ThirdGoalNewTitleDto> getNewTitle(Long id) {
        return thirdGoalRepository.findById(id).map(ThirdGoalNewTitleDto::fromEntity);
    }

    public List<ThirdGoal> getThirdGoalsBySecondGoalId(Long secondGoalId){
        return thirdGoalRepository.findBySecondGoalId(secondGoalId);
    }

    public ThirdGoal updateColor(Long id, String color){
        ThirdGoal goal = thirdGoalRepository.findById(id).orElseThrow();
        goal.setColor(color);
        return thirdGoalRepository.save(goal);
    }

    public ThirdGoalAchievementDto updateAchievementLevel(Long id, String achievement) {
        ThirdGoal goal = thirdGoalRepository.findById(id).orElseThrow(() -> new RuntimeException("ThirdGoal not found"));
        try {
            AchievementLevel level = AchievementLevel.valueOf(achievement.toUpperCase());
            goal.setAchievementLevel(level);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid achievement level: " + achievement);
        }
        ThirdGoal updatedGoal = thirdGoalRepository.save(goal);
        return ThirdGoalAchievementDto.fromEntity(updatedGoal);
    }

    public ThirdGoal updateDates(Long id, List<Date> dates){
        ThirdGoal goal = thirdGoalRepository.findById(id).orElseThrow(() -> new RuntimeException("ThirdGoal not found"));
        GoalDate goalDate = new GoalDate();
        goalDate.setDates(dates);
        goal.setGoalDate(goalDate);
        return thirdGoalRepository.save(goal);
    }
}
