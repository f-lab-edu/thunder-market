package com.github.thundermarket.thundermarket.testDouble;

import com.github.thundermarket.thundermarket.service.KeywordMatchingService;

import java.util.List;

public class FakeKeywordMatchingService extends KeywordMatchingService {
    public FakeKeywordMatchingService() {
        super(null, null);
    }

    @Override
    public List<String> findEmailsByKeywordsInTitle(String title) {
        return List.of();
    }
}
