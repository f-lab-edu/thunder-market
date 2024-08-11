package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.Keyword;
import com.github.thundermarket.thundermarket.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class KeywordCommandHandler {

    private final KeywordRepository keywordRepository;

    public Long save(Keyword keyword) {
        Keyword savedKeyword = keywordRepository.save(keyword);
        return savedKeyword.getId();
    }

    public void delete(Long keywordId) {
        keywordRepository.deleteById(keywordId);
    }
}
