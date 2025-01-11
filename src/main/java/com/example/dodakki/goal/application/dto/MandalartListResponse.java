package com.example.dodakki.goal.application.dto;

import com.example.dodakki.goal.domain.Bookmark;
import com.example.dodakki.goal.domain.Status;
import com.example.dodakki.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class MandalartListResponse {
    private Long id;
    private String name;
    private Bookmark bookmark;

    public static List<MandalartListResponse> of(User user) {
        List<MandalartListResponse> mandalartList = user.getMandalartList().stream()
                .map(mandalart -> new MandalartListResponse(
                        mandalart.getId(),
                        mandalart.getName(),
                        mandalart.getBookmark()))
                .sorted((m1, m2) -> m1.getBookmark().compareTo(m2.getBookmark()))
                .toList();
        return mandalartList;
    }
}
