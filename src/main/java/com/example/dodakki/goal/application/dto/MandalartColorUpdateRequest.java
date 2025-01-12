package com.example.dodakki.goal.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MandalartColorUpdateRequest {
    private Long mandalartId;
    private String color;
}
