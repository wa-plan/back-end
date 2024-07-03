package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.AchievementStatus;
import com.example.waplan.goal.domain.FirstGoal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseGoalDto {
    private Long id;
    private String goal;
    private String content;
    private String color;
    private boolean achieved;
    private Integer streakCount;
    private boolean favoriteStatus;

    public static <T extends BaseGoalDto> T fromEntity(FirstGoal goal, T dto){
        dto.setId(goal.getId());
        dto.setGoal(goal.getGoal());
        dto.setContent(goal.getContent());
        dto.setColor(goal.getColor());
        dto.setAchieved(goal.isAchieved());
        dto.setStreakCount(goal.getStreakCount());
        dto.setFavoriteStatus(goal.isFavoriteStatus());
        return dto;
    }
}
