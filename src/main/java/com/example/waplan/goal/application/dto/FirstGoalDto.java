package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.FirstGoal;

public class FirstGoalDto extends BaseGoalDto {
    public static FirstGoalDto fromEntity(FirstGoal goal){
        return BaseGoalDto.fromEntity(goal, new FirstGoalDto());
    }
}
