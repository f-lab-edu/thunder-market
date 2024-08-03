package com.github.thundermarket.thundermarket.unit;


import com.github.thundermarket.thundermarket.domain.Keyword;
import com.github.thundermarket.thundermarket.service.KeywordQueryHandler;
import com.github.thundermarket.thundermarket.testDouble.KeywordFakeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class KeywordQueryHandlerTest {

    private Keyword createKeyword() {
        return new Keyword(1L, "아이폰14", 1L);
    }

    @Test
    public void 등록된_키워드_조회() throws Exception {
        // given
        long userId = 1L;
        KeywordFakeRepository keywordRepository = new KeywordFakeRepository();
        KeywordQueryHandler keywordQueryHandler = new KeywordQueryHandler(keywordRepository);
        keywordRepository.save(createKeyword());

        // when
        List<Keyword> keywords = keywordQueryHandler.findAllByUserId(userId);

        // then
        Assertions.assertThat(keywords.size()).isEqualTo(1);
    }
}
