package com.github.thundermarket.thundermarket.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thundermarket.thundermarket.domain.User;
import jakarta.servlet.http.Cookie;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.github.thundermarket.thundermarket.config.TestContainersUtils.*;
import static com.github.thundermarket.thundermarket.config.TestContainersUtils.registerRedisProperties;
import static com.github.thundermarket.thundermarket.config.TestUtils.createUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Testcontainers
public class UserControllerTest {

    @Container
    static MySQLContainer<?> mysqlContainer = getMysqlContainer();

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
    public void 회원가입_성공() throws Exception {
        User user = createUser(null, "test01@email.com", "password");

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/auth/join")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입_이메일형식_실패() throws Exception {
        User user = createUser(null, "test01", "password");

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/auth/join")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 전체_회원_조회() throws Exception {
        User user = createUser(null, "test01@email.com", "password");

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/auth/join")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());

        MvcResult loginResult = mockMvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"))
                .andReturn();

        String sessionId = loginResult.getResponse().getCookie("SESSION").getValue();

        mockMvc.perform(get("/api/v1/users")
                        .contentType("application/json")
                        .cookie(new Cookie("SESSION", sessionId)))
                .andExpect(status().isOk());
    }

    @Test
    public void 로그인_성공() throws Exception {
        User user = createUser(null, "test01@email.com", "password");

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/auth/join")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"));
    }

    @Test
    public void 로그인_실패() throws Exception {
        User user = createUser(null, "test01@email.com", "password");

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/auth/join")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());


        User wrongUser = createUser(null, "test01@email.com", "wrong");
        String wrongUserJson = objectMapper.writeValueAsString(wrongUser);

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content(wrongUserJson))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));
    }

    @Test
    public void 마이페이지_조회() throws Exception {
        User user = createUser(null, "test01@email.com", "password");

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/auth/join")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());

        MvcResult loginResult = mockMvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"))
                .andReturn();

        String sessionId = loginResult.getResponse().getCookie("SESSION").getValue();

        String response = mockMvc.perform(get("/api/v1/mypage")
                        .contentType("application/json")
                        .cookie(new Cookie("SESSION", sessionId)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(user.getEmail()).isEqualTo(new JSONObject(response).getString("email"));
    }
}
