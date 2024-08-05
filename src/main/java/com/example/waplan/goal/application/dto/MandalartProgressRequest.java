package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.Status;
import lombok.*;


@Getter
@RequiredArgsConstructor
public class MandalartProgressRequest {
    private Long id;
    private Status status;
}
