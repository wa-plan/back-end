package com.example.waplan.goal.application.dto;

import com.example.waplan.user.domain.AlarmType;
import lombok.*;

@Getter
@RequiredArgsConstructor
public class SecondGoalAddRequest {
    private Long mandalartId;
    private String name;
    private String color;



}
