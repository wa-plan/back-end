package com.example.waplan.goal.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SecondGoalAddRequest {
    private Long mandalartId;
    private String name;
    private String color;



}
