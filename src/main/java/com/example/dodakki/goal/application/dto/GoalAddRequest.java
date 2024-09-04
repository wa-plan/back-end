package com.example.dodakki.goal.application.dto;

import com.example.dodakki.goal.domain.Repetition;
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
    private Repetition repetition;
}
