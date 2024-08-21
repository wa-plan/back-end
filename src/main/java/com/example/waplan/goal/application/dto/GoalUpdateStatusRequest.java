package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GoalUpdateStatusRequest {
    private Long goalId;
    private Status attainment;
}
