package com.example.dodakki.goal.application.dto;

import com.example.dodakki.goal.domain.Bookmark;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class BookmarkUpdateRequest {
    private Long id;
    private Bookmark bookmark;
}
