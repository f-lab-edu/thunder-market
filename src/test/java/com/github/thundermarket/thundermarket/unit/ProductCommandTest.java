package com.github.thundermarket.thundermarket.unit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thundermarket.thundermarket.constant.ProductStatus;
import com.github.thundermarket.thundermarket.dto.FileUploadResult;
import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.dto.ProductResponse;
import com.github.thundermarket.thundermarket.repository.FileStorage;
import com.github.thundermarket.thundermarket.service.ProductCommandHandler;
import com.github.thundermarket.thundermarket.config.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;

import static com.github.thundermarket.thundermarket.config.TestUtils.createProduct;
import static com.github.thundermarket.thundermarket.config.TestUtils.createProductDetail;

public class ProductCommandTest {

    @Test
    public void 상품_추가_성공() throws IOException {
        ProductCommandHandler productCommandHandler = new ProductCommandHandler(new ProductFakeRepository(), new ProductDetailFakeRepository(), new FileFakeStorage(), new DummyProductEventPublisher(), new FakeKeywordMatchingService());
        MockMultipartFile emptyMockMultipartFile = new MockMultipartFile("video", "test-video.mp4", "video/mp4", new FileInputStream(ResourceUtils.getFile("classpath:5sec.mp4")));
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedProductName = "iPhone12";
        String expectedProductDetailColor = "white";

        ProductResponse productResponse = productCommandHandler.add(
                createProduct(1L, "아이폰 팝니다", "iPhone12", 200_000, ProductStatus.AVAILABLE, 1L),
                createProductDetail(1L, "white", "80%", "good", 3000),
                emptyMockMultipartFile);

        String productResponseJson = objectMapper.writeValueAsString(productResponse);
        JsonNode jsonNode = objectMapper.readTree(productResponseJson);
        String productName = jsonNode.path("product").path("name").asText();
        String productDetailColor = jsonNode.path("productDetail").path("color").asText();

        Assertions.assertThat(productName).isEqualTo(expectedProductName);
        Assertions.assertThat(productDetailColor).isEqualTo(expectedProductDetailColor);
    }

    @Test
    public void 동영상_파일_저장() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test-video.mp4", "video/mp4", new FileInputStream(ResourceUtils.getFile("classpath:5sec.mp4")));
        FileStorage localFileStorage = new FileFakeStorage();
        FileUploadResult fileUploadResult = localFileStorage.save(mockMultipartFile);

        Assertions.assertThat(fileUploadResult.getVideoFilePath()).endsWith(".mp4");
    }

    @Test
    public void 동영상_파일_및_썸네일_저장() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test-video.mp4", "video/mp4", new FileInputStream(ResourceUtils.getFile("classpath:5sec.mp4")));
        ProductCommandHandler productCommandHandler = new ProductCommandHandler(new ProductFakeRepository(), new ProductDetailFakeRepository(), new FileFakeStorage(), new DummyProductEventPublisher(), new FakeKeywordMatchingService());
        ProductResponse productResponse = productCommandHandler.add(
                createProduct(1L, "아이폰 팝니다", "iPhone12", 200_000, ProductStatus.AVAILABLE, 1L),
                createProductDetail(1L, "white", "80%", "good", 3000),
                mockMultipartFile);

        String videoFilePath = productResponse.getProductDetail().getVideoFilePath();
        String thumbnailFilePath = productResponse.getProductDetail().getThumbnailFilePath();

        Assertions.assertThat(videoFilePath).endsWith(".mp4");
        Assertions.assertThat(thumbnailFilePath).endsWith(".jpg");
    }
}
