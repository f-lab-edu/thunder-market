package com.github.thundermarket.thundermarket.testDouble;

import com.github.thundermarket.thundermarket.repository.FileStorage;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;
import com.github.thundermarket.thundermarket.repository.ProductRepository;
import com.github.thundermarket.thundermarket.service.ProductService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@TestConfiguration
@EnableJdbcRepositories
//Controller 통합테스트에서 Fake 객체가 아닌 main 객체를 주입하기 위해 사용합니다.
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
