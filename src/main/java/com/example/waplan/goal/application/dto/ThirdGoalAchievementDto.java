package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.AchievementLevel;
import com.example.waplan.goal.domain.ThirdGoal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThirdGoalAchievementDto {
    private Long id;
    private AchievementLevel achievementLevel;

    public static ThirdGoalAchievementDto fromEntity(ThirdGoal goal){
        ThirdGoalAchievementDto dto = new ThirdGoalAchievementDto();
        dto.setId(goal.getId());
        dto.setAchievementLevel(goal.getAchievementLevel());
        return dto;
    }
}
