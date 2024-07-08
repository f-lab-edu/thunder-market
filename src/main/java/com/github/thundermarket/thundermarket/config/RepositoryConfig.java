package com.github.thundermarket.thundermarket.config;

import com.github.thundermarket.thundermarket.repository.*;
import com.github.thundermarket.thundermarket.service.ProductService;
import com.github.thundermarket.thundermarket.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class RepositoryConfig {

    private final DataSource dataSource;

    public RepositoryConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new MySQLUserRepository(dataSource);
    }

    @Bean
    public FileStorage fileStorage() {
        return new NCloudStorage();
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
