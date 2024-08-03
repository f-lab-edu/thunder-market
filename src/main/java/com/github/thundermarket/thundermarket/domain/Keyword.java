package com.github.thundermarket.thundermarket.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("keywords")
public class Keyword {

    @Id @GeneratedValue
    private Long id;
    private String keyword;
    private Long userId;

    public Keyword(Long id, String keyword, Long userId) {
        this.id = id;
        this.keyword = keyword;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getKeyword() {
        return keyword;
    }

    public Long getUserId() {
        return userId;
    }
}
