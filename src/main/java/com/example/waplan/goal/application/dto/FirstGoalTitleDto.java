package com.example.waplan.goal.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirstGoalTitleDto {
    private String goal;

    public static FirstGoalTitleDto fromEntity(String goal) {
        FirstGoalTitleDto dto = new FirstGoalTitleDto();
        dto.setGoal(goal);
        return dto;
    }
}
