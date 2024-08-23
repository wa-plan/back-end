package com.example.dodakki.goal.application.dto;

import com.example.dodakki.goal.domain.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GoalUpdateStatusRequest {
    private Long goalId;
    private Status attainment;
}
