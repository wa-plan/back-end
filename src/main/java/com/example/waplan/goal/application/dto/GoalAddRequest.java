package com.example.waplan.goal.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class GoalAddRequest {
    private Long thirdGoalId;
    private String name;
    private List<LocalDate> dates;

}
