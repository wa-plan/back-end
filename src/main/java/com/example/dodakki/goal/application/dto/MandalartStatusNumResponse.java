package com.example.dodakki.goal.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MandalartStatusNumResponse {
    private int successNum;
    private int inProgressNum;
    private int failed;
}
