package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.AchievementLevel;
import com.example.waplan.goal.domain.GoalDate;
import com.example.waplan.goal.domain.ThirdGoal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThirdGoalDto {
    private Long id;
    private String title;
    private String newTitle;
    private String color;
    private AchievementLevel achievementLevel;
    private GoalDate goalDates;

    public static ThirdGoalDto fromEntity(ThirdGoal goal){
        ThirdGoalDto dto = new ThirdGoalDto();
        dto.setId(goal.getId());
        dto.setTitle(goal.getTitle());
        dto.setNewTitle(goal.getNewTitle());
        dto.setNewTitle(goal.getNewTitle());
        dto.setAchievementLevel(goal.getAchievementLevel());
        dto.setGoalDates(goal.getGoalDates());

        return dto;
    }

}
