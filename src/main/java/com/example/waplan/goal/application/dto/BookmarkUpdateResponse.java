package com.example.waplan.goal.application.dto;

import com.example.waplan.goal.domain.Bookmark;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BookmarkUpdateResponse {

    private Bookmark bookmark;


}
