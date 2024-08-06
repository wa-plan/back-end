package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.Goal;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class GoalResponse {

    private Goal goal;

}
