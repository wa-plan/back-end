package com.example.waplan.goal.application.dto;


import java.time.LocalDate;
import java.util.List;

import lombok.*;


@Getter
@RequiredArgsConstructor
public class MandalartAddRequest {

    private String name;
    private String description;
    private String color;
    private LocalDate date;
    private List<String> picture;

}
