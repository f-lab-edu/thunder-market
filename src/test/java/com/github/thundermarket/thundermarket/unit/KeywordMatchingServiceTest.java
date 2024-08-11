package com.github.thundermarket.thundermarket.unit;

import com.github.thundermarket.thundermarket.domain.Keyword;
import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.service.KeywordMatchingService;
import com.github.thundermarket.thundermarket.service.KeywordQueryHandler;
import com.github.thundermarket.thundermarket.service.UserQueryHandler;
import com.github.thundermarket.thundermarket.testDouble.KeywordFakeRepository;
import com.github.thundermarket.thundermarket.testDouble.UserFakeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class KeywordMatchingServiceTest {

    private User createUser(Long id, String email, String password) {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }

    private Keyword createKeyword(Long id, String keyword, Long userId) {
        return new Keyword(id, keyword, userId);
    }

    @Test
    void findUserIdsWithMatchingKeyword() {
        KeywordFakeRepository keywordRepository = new KeywordFakeRepository();
        UserFakeRepository userFakeRepository = new UserFakeRepository();
        KeywordMatchingService keywordMatchingService = new KeywordMatchingService(new KeywordQueryHandler(keywordRepository), new UserQueryHandler(userFakeRepository));
        userFakeRepository.save(createUser(1L, "test@test.com", "password"));
        keywordRepository.save(createKeyword(1L, "아이폰14", 1L));
        keywordRepository.save(createKeyword(2L, "아이폰12", 1L));

        List<String> emails = keywordMatchingService.findEmailsByKeywordsInTitle("아이폰");

        Assertions.assertThat(emails.size()).isEqualTo(2);
    }
}