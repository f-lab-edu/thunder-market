package com.github.thundermarket.thundermarket.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thundermarket.thundermarket.dto.KeywordRequest;
import jakarta.servlet.http.Cookie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.charset.StandardCharsets;

import static com.github.thundermarket.thundermarket.config.TestContainersUtils.*;
import static com.github.thundermarket.thundermarket.config.TestUtils.getSessionId;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Testcontainers
public class KeywordControllerTest {

    @Container
    static MySQLContainer<?> mySQLContainer = getMysqlContainer();

    @Container
    static GenericContainer<?> redisContainer = getRedisContainer();

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registerMySQLProperties(registry);
        registerRedisProperties(registry);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void 키워드_목록_조회() throws Exception {
        String products = mockMvc.perform(get("/api/v1/keywords")
                        .contentType("application/json")
                        .cookie(new Cookie("SESSION", getSessionId(mockMvc, objectMapper))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat(products).isEqualTo("{\"keywords\":[{\"id\":1,\"keyword\":\"아이폰\",\"userId\":1}]}");
    }

    @Test
    public void 키워드_등록() throws Exception {
        // given
        KeywordRequest keywordRequest = new KeywordRequest("아이폰15");
        String keywordRequestJson = objectMapper.writeValueAsString(keywordRequest);
        // when

        // then
        mockMvc.perform(post("/api/v1/keywords")
                        .contentType("application/json")
                        .content(keywordRequestJson)
                        .cookie(new Cookie("SESSION", getSessionId(mockMvc, objectMapper))))
                .andExpect(status().isOk());
    }

    @Test
    public void 키워드_삭제() throws Exception {
        // given

        // when

        // then
        mockMvc.perform(delete("/api/v1/keywords/{keywordId}", 1)
                        .cookie(new Cookie("SESSION", getSessionId(mockMvc, objectMapper))))
                .andExpect(status().isOk());
    }

}
