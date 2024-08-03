package com.github.thundermarket.thundermarket.domain;

import org.springframework.data.annotation.Id;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Table("keywords")
@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Keyword {

    @Id
    private Long id;
    private String keyword;
    private Long userId;
}
