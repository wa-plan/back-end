package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.SecondGoal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecondGoalDto {
    private Long id;
    private String title;
    private String field;
    private List<ThirdGoalDto> thirdGoals;

    private SecondGoalDto fromEntity(SecondGoal goal){
        SecondGoalDto dto = new SecondGoalDto();
        dto.setId(goal.getId());
        dto.setTitle(goal.getTitle());
        dto.setField(goal.getField());
        dto.setThirdGoals(goal.getThirdGoals().stream()
            .map(ThirdGoalDto::fromEntity)
            .collect(Collectors.toList()));

        return dto;
    }
}
