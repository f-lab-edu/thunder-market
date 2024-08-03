package com.github.thundermarket.thundermarket.dto;

import com.github.thundermarket.thundermarket.domain.Keyword;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class KeywordResponse {

    private final List<Keyword> keywords;

    public static KeywordResponse of(List<Keyword> keywords) {
        return new KeywordResponse(keywords);
    }
}
