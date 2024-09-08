package com.example.dodakki.goal.application.dto;

import com.example.dodakki.goal.domain.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class GoalUpdateStatusRequest {
    private Long goalId;
    private Status attainment;
    private LocalDate date;
}
