package com.example.dodakki.goal.application.dto;

import com.example.dodakki.Photo.domain.Photo;
import com.example.dodakki.goal.domain.Mandalart;
import com.example.dodakki.goal.domain.SecondGoal;
import com.example.dodakki.goal.domain.Status;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MandalartResponse {
    private Long id;
    private String name;
    private Integer dDay;
    private Status status;
    private List<Photo> photoList;
    private String description;
    private List<SecondGoalRes> secondGoalRes;
    private MandalartStatusNumResponse statusNum;

    public static MandalartResponse of(Mandalart mandalart, MandalartStatusNumResponse statusNum) {
        return new MandalartResponse(mandalart.getId(), mandalart.getName(), Period.between(LocalDate.now(), mandalart.getGoalDate().getDate()).getDays(), mandalart.getStatus(), mandalart.getPhotoList(), mandalart.getDescription(), SecondGoalRes.of(mandalart), statusNum);
    }
}

@Getter
@NoArgsConstructor
class SecondGoalRes {
    private Long secondGoalId;
    private String secondGoalName;

    public SecondGoalRes(Long secondGoalId, String secondGoalName) {
        this.secondGoalId = secondGoalId;
        this.secondGoalName = secondGoalName;
    }

    public static List<SecondGoalRes> of(Mandalart mandalart) {
        return mandalart.getSecondGoalList().stream()
                .map(secondGoal -> new SecondGoalRes(secondGoal.getId(), secondGoal.getName()))
                .collect(Collectors.toList());
    }
}

