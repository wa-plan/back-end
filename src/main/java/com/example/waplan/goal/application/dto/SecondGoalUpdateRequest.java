package com.example.waplan.goal.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SecondGoalUpdateRequest {
    private Long secondGoalId;
    private String newSecondGoal;
}
