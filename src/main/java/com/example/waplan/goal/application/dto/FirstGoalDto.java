package com.example.waplan.goal.application.dto;
import com.example.waplan.goal.domain.AchievementStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirstGoalDto {
    private String goal;
    private String content;
    private String color;
    private AchievementStatus achievementStatus;
    private Integer streakCount;
    private boolean favoriteStatus;
}
