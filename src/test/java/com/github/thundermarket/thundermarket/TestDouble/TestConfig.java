package com.github.thundermarket.thundermarket.TestDouble;

import com.github.thundermarket.thundermarket.repository.FileStorage;
import com.github.thundermarket.thundermarket.repository.NCloudStorage;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;
import com.github.thundermarket.thundermarket.repository.ProductRepository;
import com.github.thundermarket.thundermarket.service.ProductService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public FileStorage fileStorage() {
        return new FileFakeStorage();
    }

    @Bean
    public ProductService productService(
            ProductRepository productRepository,
            ProductDetailRepository productDetailRepository
    ) {
        return new ProductService(
                productRepository,
                productDetailRepository,
                fileStorage()
        );
    }
}
