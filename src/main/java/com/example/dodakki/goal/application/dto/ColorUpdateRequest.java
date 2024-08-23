package com.example.dodakki.goal.application.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ColorUpdateRequest {
    private Long secondGoalId;
    private String color;
}
