package com.example.waplan.goal.application.dto;
import com.example.waplan.goal.domain.AchievementStatus;
import com.example.waplan.goal.domain.FirstGoal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirstGoalDto {
    private Long id;
    private String userId;
    private String goal;
    private String content;
    private String color;
    private AchievementStatus achievementStatus;
    private Integer streakCount;
    private boolean favoriteStatus;

    public static FirstGoalDto fromEntity(FirstGoal goal){
        FirstGoalDto dto = new FirstGoalDto();
        dto.setId(goal.getId());
        dto.setGoal(goal.getGoal());
        dto.setContent(goal.getContent());
        dto.setColor(goal.getColor());
        dto.setAchievementStatus(goal.getAchievementStatus());
        dto.setStreakCount(goal.getStreakCount());
        dto.setFavoriteStatus(goal.isFavoriteStatus());
        return dto;
    }
}
