package com.github.thundermarket.thundermarket.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thundermarket.thundermarket.config.TestConfig;
import com.github.thundermarket.thundermarket.domain.User;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static com.github.thundermarket.thundermarket.config.TestUtils.createUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Testcontainers
@Import(TestConfig.class)
public class ProductDetailControllerTest {

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

    @Test
    public void 상품상세정보_존재하지않으면_404응답() throws Exception {
        mockMvc.perform(get("/api/v1/products/0")
                        .contentType("application/json")
                        .cookie(new Cookie("SESSION", getSessionId())))
                .andExpect(status().isNotFound());
    }

    @Test
    public void 상품상세정보_존재하면_200응답() throws Exception {
        String productDetailId = "1";
        mockMvc.perform(get("/api/v1/products/" + productDetailId)
                        .contentType("application/json")
                        .cookie(new Cookie("SESSION", getSessionId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productDetail.color").value("white"));
    }

    private String getSessionId() throws Exception {
        return mockMvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createUser(1L, "jaen6563@naver.com", "password"))))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"))
                .andReturn().getResponse().getCookie("SESSION").getValue();
    }
}
