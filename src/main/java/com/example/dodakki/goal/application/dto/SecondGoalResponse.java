package com.example.dodakki.goal.application.dto;

import com.example.dodakki.goal.domain.SecondGoal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class SecondGoalResponse {
    private Long id;
    private String secondGoal;
    private String color;
    private List<ThirdGoalResponse> thirdGoals;

    public static List<ThirdGoalResponse> of(SecondGoal secondGoal) {
        return secondGoal.getThridGoalList().stream()
                .map(thirdGoal -> new ThirdGoalResponse(thirdGoal.getId(), thirdGoal.getName())).collect(Collectors.toList());
    }
}
