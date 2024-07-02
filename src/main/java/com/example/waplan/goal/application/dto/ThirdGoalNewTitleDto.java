package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.ThirdGoal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThirdGoalNewTitleDto {
    private Long id;
    private String newTitle;

    public static ThirdGoalNewTitleDto fromEntity(ThirdGoal goal){
        ThirdGoalNewTitleDto dto = new ThirdGoalNewTitleDto();
        dto.setId(goal.getId());
        dto.setNewTitle(goal.getNewTitle());
        return dto;
    }
}
