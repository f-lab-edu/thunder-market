package com.github.thundermarket.thundermarket.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class KeywordMatchingService {

    private final KeywordQueryHandler keywordQueryHandler;
    private final UserQueryHandler userQueryHandler;

    public KeywordMatchingService(KeywordQueryHandler keywordQueryHandler, UserQueryHandler userQueryHandler) {
        this.keywordQueryHandler = keywordQueryHandler;
        this.userQueryHandler = userQueryHandler;
    }

    public List<String> findEmailsByKeywordsInTitle(String title) {
        List<Long> userIds = keywordQueryHandler.findUserIdsWithMatchingKeyword(title);
        return userQueryHandler.findEmailByUserIdIn(userIds);
    }
}
