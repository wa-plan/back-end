package com.example.waplan.goal.application.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirstGoalAddDto {
    private String goal;
    private String content;
    private String color;
    private Date date;
    private String picture;
}
