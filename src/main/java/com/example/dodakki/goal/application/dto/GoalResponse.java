package com.example.dodakki.goal.application.dto;

import com.example.dodakki.goal.domain.Goal;
import com.example.dodakki.goal.domain.Repeat;
import com.example.dodakki.goal.domain.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class GoalResponse {

    private Long id;
    private String goalName;
    private String color;
    private String thridGoal;
    private Status attainment;
    private Repeat repeat;
    public static GoalResponse of(Goal goal){
        return new GoalResponse(goal.getId(), goal.getName(), goal.getThirdGoal().getSecondGoal().getColor(), goal.getThirdGoal().getName(), goal.getAttainment(), goal.getRepeat());
    }
    public static List<GoalResponse> listOf(List<Goal> goals) {
        List<GoalResponse> goalResponses = new ArrayList<>();

        for(Goal goal : goals){
            goalResponses.add(of(goal));
        }
        return goalResponses;
    }
}
