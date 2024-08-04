package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.Bookmark;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BookmarkUpdateRequest {
    private Long id;
    private Bookmark bookmark;
}
