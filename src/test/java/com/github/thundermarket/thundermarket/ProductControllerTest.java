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
import org.springframework.test.web.servlet.MockMvc;
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
@Import(TestConfig.class)
public class ProductControllerTest {

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

    @Test
    public void 상품1개_상품목록조회() throws Exception {
        mockMvc.perform(get("/api/v1/products")
                        .contentType("application/json")
                        .cookie(new Cookie("SESSION", getSessionId())))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"products\":[{\"id\":1,\"title\":\"아이폰 팝니다\",\"name\":\"iPhone11\",\"price\":200000,\"status\":\"available\",\"userId\":1}],\"cursorId\":1,\"limit\":10}"));
    }

    @Test
    public void 상품_등록_성공() throws Exception {
        String productRequestJson = "{\n" +
                "  \"product\": {\n" +
                "    \"title\": \"아이폰 팝니다\",\n" +
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
                        .cookie(new Cookie("SESSION", getSessionId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.name").value("iPhone11"))
                .andExpect(jsonPath("$.productDetail.color").value("white"))
                .andExpect(jsonPath("$.productDetail.videoFilePath").value(endsWith(".mp4")));
    }

    @Test
    public void 상품조회_상품옵션필터링_검색결과없음() throws Exception {
        String name = "iPhone18";

        mockMvc.perform(get("/api/v1/products/filter")
                        .contentType("multipart/form-data")
                        .param("name", name)
                        .cookie(new Cookie("SESSION", getSessionId())))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"products\":[],\"cursorId\":null,\"limit\":0}"));
    }

    @Test
    public void 상품조회_상품옵션필터링() throws Exception {
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
                        .cookie(new Cookie("SESSION", getSessionId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products[0].name").value("iPhone11"))
                .andExpect(jsonPath("$.products[0].price").value(200000));
    }

    @Test
    public void 상품조회_상품제목_키워드검색() throws Exception {
        String keyword = "팝니다";
        String expectedKeyword = "아이폰 팝니다";

        mockMvc.perform(get("/api/v1/products/keyword")
                        .contentType("multipart/form-data")
                        .param("keyword", keyword)
                        .cookie(new Cookie("SESSION", getSessionId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products[0].title").value(expectedKeyword));
    }

    @Test
    public void 상품_판매이력_조회() throws Exception {
        String expectedProductName = "iPhone11";

        mockMvc.perform(get("/api/v1/products/history/sales")
                        .contentType("multipart/form-data")
                        .cookie(new Cookie("SESSION", getSessionId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products[0].name").value(expectedProductName));
    }

    private String getSessionId() throws Exception {
        return mockMvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createUser("test01@email.com", "password"))))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"))
                .andReturn().getResponse().getCookie("SESSION").getValue();
    }
}
