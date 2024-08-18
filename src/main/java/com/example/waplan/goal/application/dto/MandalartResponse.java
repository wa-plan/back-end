package com.example.waplan.goal.application.dto;

import com.example.waplan.Photo.domain.Photo;
import com.example.waplan.goal.domain.Mandalart;
import com.example.waplan.goal.domain.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MandalartResponse {

    private String name;
    private Integer dDay;
    private Status status;
    private List<Photo> photoList;
    private String description;
    private Double attainment;

    public static MandalartResponse of(Mandalart mandalart) {
        double attainmentRate;
        if(mandalart.getGoalCount() == 0) {
            attainmentRate = 0;
        }
        else {
            attainmentRate = (double) mandalart.getAttainmentCount() / mandalart.getGoalCount();
        }
        return new MandalartResponse(mandalart.getName(), Period.between(LocalDate.now(), mandalart.getGoalDate().getDate()).getDays(), mandalart.getStatus(), mandalart.getPhotoList(), mandalart.getDescription(), attainmentRate);
    }
}
