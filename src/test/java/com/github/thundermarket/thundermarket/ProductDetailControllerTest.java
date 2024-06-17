package com.github.thundermarket.thundermarket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void 상품상세정보_존재하지않으면_400응답() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userEmail", "test01@test.com");

        mockMvc.perform(get("/api/v1/products/0")
                        .contentType("application/json")
                        .session(session))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql("/productDetailControllerTest.sql")
    public void 상품상세정보_존재하면_200응답() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userEmail", "test01@test.com");

        mockMvc.perform(get("/api/v1/products/1")
                        .contentType("application/json")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value("white"));
    }
}
