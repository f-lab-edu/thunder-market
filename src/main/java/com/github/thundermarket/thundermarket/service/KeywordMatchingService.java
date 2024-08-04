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

    public List<String> findEmailByUserIdIn(String keyword) {
        List<Long> userIds = keywordQueryHandler.findUserIdsWithMatchingKeyword(keyword);
        return userQueryHandler.findEmailByUserIdIn(userIds);
    }
}
