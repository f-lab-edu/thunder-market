package com.github.thundermarket.thundermarket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    private User createUser(String email, String password) {
        return new User(email, password);
    }

    @Test
    public void 회원가입_성공() throws Exception {
        User user = createUser("test01@email.com", "password");

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/auth/join")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입_이메일형식_실패() throws Exception {
        User user = createUser("test01", "password");

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/auth/join")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 전체_회원_조회() throws Exception {
        User user = createUser("test01@email.com", "password");

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/auth/join")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/users")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void 로그인_성공() throws Exception {
        User user = createUser("test01@email.com", "password");

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
        User user = createUser("test01@email.com", "password");

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/auth/join")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());


        User wrongUser = createUser("test01@email.com", "wrong");
        String wrongUserJson = objectMapper.writeValueAsString(wrongUser);

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content(wrongUserJson))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));
    }

    @Test
    public void 마이페이지_조회_성공() throws Exception {
        User user = createUser("test01@email.com", "password");

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/auth/join")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());

        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content(userJson)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"));

        mockMvc.perform(get("/api/v1/auth/mypage")
                        .contentType("application/json")
                        .session(session))
                .andExpect(status().isOk());
    }

    @Test
    public void 마이페이지_조회_실패() throws Exception {
        User user = createUser("test01@email.com", "password");

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

        mockMvc.perform(get("/api/v1/auth/mypage")
                        .contentType("application/json"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Unauthorized"));
    }
}
