package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.Keyword;
import com.github.thundermarket.thundermarket.repository.KeywordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordCommandHandler {

    private final KeywordRepository keywordRepository;

    public KeywordCommandHandler(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public void save(Keyword keyword) {
        keywordRepository.save(keyword);
    }

    public void delete(Long keywordId) {
        keywordRepository.deleteById(keywordId);
    }
}
