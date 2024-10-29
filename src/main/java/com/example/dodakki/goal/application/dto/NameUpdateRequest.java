package com.example.dodakki.goal.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NameUpdateRequest {
    private Long mandalartId;
    private String name;
}
