package com.example.dodakki.goal.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class MandalartDateUpdateRequest {
    private final Long mandalartId;  // 수정할 Mandalart ID
    private final LocalDate newDate; // 변경할 새 날짜
}
