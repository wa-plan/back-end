package com.example.dodakki.goal.application.dto;

import com.example.dodakki.goal.domain.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class MandalartProgressRequest {
    private Long id;
    private Status status;
}
