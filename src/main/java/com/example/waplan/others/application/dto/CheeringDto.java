package com.example.waplan.others.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheeringDto {
    private String message;
    private String author;

    public CheeringDto(String message, String author){
        this.message = message;
        this.author = author;
    }
}
