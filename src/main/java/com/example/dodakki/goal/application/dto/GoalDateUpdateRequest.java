package com.example.dodakki.goal.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class GoalDateUpdateRequest {
    private final Long goalId;
    private final LocalDate oldDate;
    private final LocalDate newDate;
}
