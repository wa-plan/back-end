package com.example.waplan.goal.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class GoalRequest {

    private LocalDate date;
}
