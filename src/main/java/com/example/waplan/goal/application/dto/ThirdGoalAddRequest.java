package com.example.waplan.goal.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ThirdGoalAddRequest {
    private Long secondGoalId;
    private String name;
}
