package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.Mandalart;
import com.example.waplan.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class MandalartListResponse {
    private Long id;
    private String name;

    public static List<MandalartListResponse> of(User user) {
        List<MandalartListResponse> mandalartList = user.getMandalartList().stream().map(
                mandalart -> new MandalartListResponse(mandalart.getId(), mandalart.getName())).toList();
        return mandalartList;
    }
}
