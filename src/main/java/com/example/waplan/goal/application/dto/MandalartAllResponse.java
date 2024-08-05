package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.Mandalart;
import com.example.waplan.goal.domain.SecondGoal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class MandalartAllResponse {
    private String mandalart;
    private List<SecondGoalResponse> secondGoals;


    public static MandalartAllResponse of(Mandalart mandalart) {
        List<SecondGoalResponse> secondGoalList = mandalart.getSecondGoalList().stream().map(
                secondGoal -> new SecondGoalResponse(secondGoal.getName(), SecondGoalResponse.of(secondGoal))
        ).toList();
        return new MandalartAllResponse(mandalart.getName(), secondGoalList);
    }
}
