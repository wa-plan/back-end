package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.FirstGoal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirstGoalDetailDto extends BaseGoalDto {
    private List<SecondGoalDto> secondGoals;

    public static FirstGoalDetailDto fromEntity(FirstGoal goal){
        FirstGoalDetailDto dto = BaseGoalDto.fromEntity(goal, new FirstGoalDetailDto());
        dto.setSecondGoals(goal.getSecondGoals().stream()
            .map(SecondGoalDto::fromEntity)
            .collect(Collectors.toList()));
        return dto;
    }
}
