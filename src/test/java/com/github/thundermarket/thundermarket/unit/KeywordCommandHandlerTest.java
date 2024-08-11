package com.github.thundermarket.thundermarket.unit;

import com.github.thundermarket.thundermarket.service.KeywordCommandHandler;
import com.github.thundermarket.thundermarket.config.KeywordFakeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.thundermarket.thundermarket.config.TestUtils.createKeyword;

public class KeywordCommandHandlerTest {

    @Test
    public void 키워드_등록() throws Exception {
        // given
        long keywordId = 1L;
        KeywordFakeRepository keywordRepository = new KeywordFakeRepository();
        KeywordCommandHandler keywordCommandHandler = new KeywordCommandHandler(keywordRepository);

        // when
        keywordCommandHandler.save(createKeyword(1L, "아이폰14", 1L));

        // then
        Assertions.assertThat(keywordRepository.findById(keywordId).get().getId()).isEqualTo(keywordId);
    }

    @Test
    public void 키워드_삭제() throws Exception {
        // given
        long keywordId = 1L;
        KeywordFakeRepository keywordRepository = new KeywordFakeRepository();
        KeywordCommandHandler keywordCommandHandler = new KeywordCommandHandler(keywordRepository);
        keywordRepository.save(createKeyword(1L, "아이폰14", 1L));

        // when
        keywordCommandHandler.delete(keywordId);

        // then
        Assertions.assertThat(keywordRepository.findAll().size()).isEqualTo(0);
    }
}
