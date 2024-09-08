package com.example.dodakki.goal.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GoalUpdateRequest {

    private Long goalId;
    private String newGoal;
}
