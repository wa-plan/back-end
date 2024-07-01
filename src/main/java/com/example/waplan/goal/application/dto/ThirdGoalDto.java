package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.AchievementLevel;
import com.example.waplan.goal.domain.GoalDate;
import com.example.waplan.goal.domain.ThirdGoal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThirdGoalDto {
    private Long id;
    private String title;
    private AchievementLevel achievementLevel;
    private GoalDate goalDate;

    public static ThirdGoalDto fromEntity(ThirdGoal goal){
        ThirdGoalDto dto = new ThirdGoalDto();
        dto.setId(goal.getId());
        dto.setTitle(goal.getTitle());
        dto.setAchievementLevel(goal.getAchievementLevel());
        dto.setGoalDate(goal.getGoalDate());

        return dto;
    }
}
