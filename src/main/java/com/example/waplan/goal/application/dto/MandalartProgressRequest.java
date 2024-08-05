package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class MandalartProgressRequest {
    private Long id;
    private Status status;
}
