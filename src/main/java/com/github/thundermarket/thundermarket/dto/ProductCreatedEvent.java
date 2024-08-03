package com.github.thundermarket.thundermarket.dto;

import com.github.thundermarket.thundermarket.domain.Keyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ProductCreatedEvent {

    private final String title;
    private final String name;
    private final int price;
    private final String email;
}
