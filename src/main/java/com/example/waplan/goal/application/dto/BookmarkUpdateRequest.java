package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.Bookmark;
import lombok.*;


@Getter
@RequiredArgsConstructor
public class BookmarkUpdateRequest {
    private Long id;
    private Bookmark bookmark;
}
