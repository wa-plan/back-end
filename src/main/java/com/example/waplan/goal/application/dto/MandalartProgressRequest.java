package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MandalartProgressRequest {
    private String name;
    private Status status;
}
