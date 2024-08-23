package com.example.dodakki.goal.application.dto;

import com.example.dodakki.goal.domain.Bookmark;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BookmarkUpdateResponse {

    private Bookmark bookmark;


}
