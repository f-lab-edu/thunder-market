package com.github.thundermarket.thundermarket.unit;

import com.github.thundermarket.thundermarket.domain.Keyword;
import com.github.thundermarket.thundermarket.service.KeywordCommandHandler;
import com.github.thundermarket.thundermarket.testDouble.KeywordFakeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class KeywordCommandHandlerTest {

    private Keyword createKeyword() {
        return new Keyword(1L, "아이폰14", 1L);
    }

    @Test
    public void 키워드_등록() throws Exception {
        // given
        long keywordId = 1L;
        KeywordFakeRepository keywordRepository = new KeywordFakeRepository();
        KeywordCommandHandler keywordCommandHandler = new KeywordCommandHandler(keywordRepository);

        // when
        keywordCommandHandler.save(createKeyword());

        // then
        Assertions.assertThat(keywordRepository.findById(keywordId).get().getId()).isEqualTo(keywordId);
    }

    @Test
    public void 키워드_삭제() throws Exception {
        // given
        long keywordId = 1L;
        KeywordFakeRepository keywordRepository = new KeywordFakeRepository();
        KeywordCommandHandler keywordCommandHandler = new KeywordCommandHandler(keywordRepository);
        keywordRepository.save(createKeyword());

        // when
        keywordCommandHandler.delete(keywordId);

        // then
        Assertions.assertThat(keywordRepository.findAll().size()).isEqualTo(0);
    }
}
