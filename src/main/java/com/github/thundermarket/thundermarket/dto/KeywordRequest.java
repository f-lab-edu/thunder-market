package com.github.thundermarket.thundermarket.dto;

import com.github.thundermarket.thundermarket.domain.Keyword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KeywordRequest {

    private String keyword;

    public Keyword to(Long userId) {
        return Keyword.builder()
                .keyword(keyword)
                .userId(userId)
                .build();
    }
}
