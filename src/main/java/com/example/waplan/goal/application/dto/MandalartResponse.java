package com.example.waplan.goal.application.dto;

import com.example.waplan.Photo.domain.Photo;
import com.example.waplan.goal.domain.Mandalart;
import com.example.waplan.goal.domain.Status;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
        return new MandalartResponse(mandalart.getName(), mandalart.getDDay(), mandalart.getStatus(), mandalart.getPhotoList(), mandalart.getDescription(), attainmentRate);
    }
}
