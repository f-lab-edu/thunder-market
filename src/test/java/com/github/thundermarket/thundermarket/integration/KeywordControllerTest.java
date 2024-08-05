package com.github.thundermarket.thundermarket.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.dto.KeywordRequest;
import com.github.thundermarket.thundermarket.service.KeywordCommandHandler;
import com.github.thundermarket.thundermarket.service.KeywordQueryHandler;
import jakarta.servlet.http.Cookie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Testcontainers
public class KeywordControllerTest {

    @Container
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("schemaWithData.sql");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Container
    public static GenericContainer redis = new GenericContainer(DockerImageName.parse("redis:6.2-alpine"))
            .withExposedPorts(6379);

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", redis::getHost);
        registry.add("spring.data.redis.port", redis::getFirstMappedPort);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private User createUser(String email, String password) {
        return new User.Builder()
                .withEmail(email)
                .withPassword(password)
                .build();
    }

    private String getSessionId() throws Exception {
        return mockMvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createUser("jaen6563@naver.com", "password"))))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"))
                .andReturn().getResponse().getCookie("SESSION").getValue();
    }

    @Test
    public void 키워드_목록_조회() throws Exception {
        String products = mockMvc.perform(get("/api/v1/keywords")
                        .contentType("application/json")
                        .cookie(new Cookie("SESSION", getSessionId())))
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
                        .cookie(new Cookie("SESSION", getSessionId())))
                .andExpect(status().isOk());
    }

    @Test
    public void 키워드_삭제() throws Exception {
        // given

        // when

        // then
        mockMvc.perform(delete("/api/v1/keywords/{keywordId}", 1)
                        .cookie(new Cookie("SESSION", getSessionId())))
                .andExpect(status().isOk());
    }

}
