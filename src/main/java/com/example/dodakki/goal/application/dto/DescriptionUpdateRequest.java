package com.example.dodakki.goal.application.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DescriptionUpdateRequest {
    private Long mandalartId;
    private String description;
}
