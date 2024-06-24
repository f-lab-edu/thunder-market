package com.github.thundermarket.thundermarket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.domain.ProductResponse;
import com.github.thundermarket.thundermarket.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductServiceAddTest {

    public Product createProduct() {
        return new Product.Builder()
                .withId(1L)
                .withName("iPhone12")
                .withPrice(200_000)
                .withStatus("판매중")
                .build();
    }

    public ProductDetail createProductDetail() {
        return new ProductDetail.Builder()
                .withId(1L)
                .withColor("white")
                .withBatteryCondition("80%")
                .withCameraCondition("good")
                .withDeliveryFee(3000)
                .build();
    }

    @Test
    public void 상품_추가_성공() throws JsonProcessingException {
        ProductService productService = new ProductService(new ProductFakeRepository(), new ProductDetailFakeRepository());
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedProductName = "iPhone12";
        String expectedProductDetailColor = "white";

        ProductResponse productResponse = productService.add(createProduct(), createProductDetail());

        String productResponseJson = objectMapper.writeValueAsString(productResponse);
        JsonNode jsonNode = objectMapper.readTree(productResponseJson);
        String productName = jsonNode.path("product").path("name").asText();
        String productDetailColor = jsonNode.path("productDetail").path("color").asText();

        Assertions.assertThat(productName).isEqualTo(expectedProductName);
        Assertions.assertThat(productDetailColor).isEqualTo(expectedProductDetailColor);
    }
}
