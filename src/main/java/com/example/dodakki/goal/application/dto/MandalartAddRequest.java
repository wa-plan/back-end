package com.example.dodakki.goal.application.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Getter
@RequiredArgsConstructor
public class MandalartAddRequest {

    private String name;
    private String description;
    private String color;
    private LocalDate date;
    private List<String> picture;

}
