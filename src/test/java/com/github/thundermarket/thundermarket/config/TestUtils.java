package com.github.thundermarket.thundermarket.config;

import com.github.thundermarket.thundermarket.domain.Keyword;
import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.domain.User;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestUtils {
    public static User createUser(Long id, String email, String password) {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }

    public static Product createProduct(Long id, String title, String name, int price, String status, Long userId) {
        return Product.builder()
                .id(id)
                .title(title)
                .name(name)
                .price(price)
                .status(status)
                .userId(userId)
                .build();
    }

    public static ProductDetail createProductDetail(Long id, String color, String batteryCondition, String cameraCondition, int deleveryFee) {
        return ProductDetail.builder()
                .id(id)
                .color(color)
                .batteryCondition(batteryCondition)
                .cameraCondition(cameraCondition)
                .deliveryFee(deleveryFee)
                .build();
    }

    public static Keyword createKeyword(Long id, String keyword, Long userId) {
        return Keyword.builder()
                .id(id)
                .keyword(keyword)
                .userId(userId)
                .build();
    }

    public static String getSessionId(MockMvc mockMvc, ObjectMapper objectMapper) throws Exception {
        return mockMvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createUser(1L, "jaen6563@naver.com", "password"))))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"))
                .andReturn().getResponse().getCookie("SESSION").getValue();
    }
}
