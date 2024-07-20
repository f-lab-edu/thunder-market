package com.github.thundermarket.thundermarket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thundermarket.thundermarket.TestDouble.TestConfig;
import com.github.thundermarket.thundermarket.domain.User;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import java.io.FileInputStream;

import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Testcontainers
@Import(TestConfig.class) // 통합 테스트에서 Fake 객체를 주입하기 위한 설정
public class ProductControllerTest {

    @Container
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("schema.sql");

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

    @Test
    public void 상품0개_상품목록조회() throws Exception {
        User user = createUser("test01@email.com", "password");
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

        mockMvc.perform(get("/api/v1/products")
                        .contentType("application/json")
                        .cookie(new Cookie("SESSION", sessionId)))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"products\":[],\"cursorId\":null,\"limit\":10}"));
    }

    @Test
    public void 상품_등록_성공() throws Exception {
        User user = createUser("test01@email.com", "password");
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

        MockMultipartFile productRequest = new MockMultipartFile("productRequest", "", "application/json", productRequestJson.getBytes());
        MockMultipartFile mockMultipartFile = new MockMultipartFile("video", "test-video.mp4", "video/mp4", new FileInputStream(ResourceUtils.getFile("classpath:5sec.mp4")));

        mockMvc.perform(multipart("/api/v1/products")
                        .file(productRequest)
                        .file(mockMultipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .cookie(new Cookie("SESSION", sessionId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.name").value("iPhone11"))
                .andExpect(jsonPath("$.productDetail.color").value("white"))
                .andExpect(jsonPath("$.productDetail.videoFilePath").value(endsWith(".mp4")));
    }

    @Test
    @Sql("/productFilterTest.sql")
    public void 상품조회_상품옵션필터링() throws Exception {
        User user = createUser("test01@email.com", "password");
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

        // 사용자 입력 값: 검색 조건 설정
        String name = "iPhone11";
        String priceMin = "1000";
        String priceMax = "300000";
        String color = "white";
        String purchaseDateMin = "2022-01-01";
        String purchaseDateMax = "2024-07-17";

        mockMvc.perform(get("/api/v1/products/filter")
                        .contentType("multipart/form-data")
                        .param("name", name)
                        .param("priceMin", priceMin)
                        .param("priceMax", priceMax)
                        .param("color", color)
                        .param("purchaseDateMin", purchaseDateMin)
                        .param("purchaseDateMax", purchaseDateMax)
                        .cookie(new Cookie("SESSION", sessionId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products[0].name").value("iPhone11"))
                .andExpect(jsonPath("$.products[0].price").value(200000));
    }
}
