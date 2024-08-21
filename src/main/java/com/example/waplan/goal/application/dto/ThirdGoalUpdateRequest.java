package com.example.waplan.goal.application.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ThirdGoalUpdateRequest {
    private Long thirdGoalId;
    private String newThirdGoal;
}
