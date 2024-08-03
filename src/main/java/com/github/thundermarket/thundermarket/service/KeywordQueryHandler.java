package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.Keyword;
import com.github.thundermarket.thundermarket.repository.KeywordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordQueryHandler {

    private final KeywordRepository keywordRepository;

    public KeywordQueryHandler(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public List<Keyword> findAllByUserId(long userId) {
        return keywordRepository.findAllByUserId(userId);
    }
}
