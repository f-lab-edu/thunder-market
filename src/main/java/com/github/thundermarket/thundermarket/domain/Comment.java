package com.github.thundermarket.thundermarket.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("comments")
@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
public class Comment {

    @Id
    private Long id;
    private String text;
    private Long userId;
    private Long productId;
}
