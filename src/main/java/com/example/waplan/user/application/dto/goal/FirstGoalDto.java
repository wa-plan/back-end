package com.example.waplan.user.application.dto.goal;
import com.example.waplan.user.domain.goal.AchievementStatus;
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
