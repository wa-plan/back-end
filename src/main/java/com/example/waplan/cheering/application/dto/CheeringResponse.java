package com.example.waplan.cheering.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheeringResponse {
    private String message;
    private String author;

    public CheeringResponse(String message, String author){
        this.message = message;
        this.author = author;
    }
}
