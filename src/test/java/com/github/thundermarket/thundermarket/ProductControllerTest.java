package com.github.thundermarket.thundermarket;

import com.github.thundermarket.thundermarket.constant.SessionConst;
import com.github.thundermarket.thundermarket.domain.SessionUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 상품0개_상품목록조회() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.SESSION_USER, new SessionUser());

        mockMvc.perform(get("/api/v1/products")
                        .contentType("application/json")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"products\":[]}"));
    }

    @Test
    public void 상품_등록_성공() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.SESSION_USER, new SessionUser());

        String productRequestJson = "{\n" +
                "  \"product\": {\n" +
                "    \"name\": \"iPhone11\",\n" +
                "    \"price\": 200000,\n" +
                "    \"status\": \"available\"\n" +
                "  },\n" +
                "  \"productDetail\": {\n" +
                "    \"color\": \"white\",\n" +
                "    \"productCondition\": \"New\",\n" +
                "    \"batteryCondition\": \"Good\",\n" +
                "    \"cameraCondition\": \"Good\",\n" +
                "    \"accessories\": \"Charger, Earphones\",\n" +
                "    \"purchaseDate\": \"2023-01-01\",\n" +
                "    \"warrantyDuration\": \"12 months\",\n" +
                "    \"tradeLocation\": \"Seoul\",\n" +
                "    \"deliveryFee\": 5000\n" +
                "  }\n" +
                "}";

        mockMvc.perform(post("/api/v1/products")
                        .contentType("application/json")
                        .session(session)
                        .content(productRequestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.name").value("iPhone11"))
                .andExpect(jsonPath("$.productDetail.color").value("white"));
    }
}
