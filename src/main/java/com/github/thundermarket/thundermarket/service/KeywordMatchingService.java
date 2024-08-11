package com.github.thundermarket.thundermarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KeywordMatchingService {

    private final KeywordQueryHandler keywordQueryHandler;
    private final UserQueryHandler userQueryHandler;

    public List<String> findEmailsByKeywordsInTitle(String title) {
        List<Long> userIds = keywordQueryHandler.findUserIdsWithMatchingKeyword(title);
        return userQueryHandler.findEmailByUserIdIn(userIds);
    }
}
