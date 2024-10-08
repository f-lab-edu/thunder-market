package com.github.thundermarket.thundermarket.config;

import com.github.thundermarket.thundermarket.repository.FileStorage;
import com.github.thundermarket.thundermarket.service.EmailService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@TestConfiguration
// 이 설정은 Controller 통합테스트에서 사용할 빈을 수동으로 주입해주기 위해 사용합니다.
public class TestConfig {

    @Bean
    public FileStorage fileStorage() {
        return new FileFakeStorage();
    }

    @Bean
    public EmailService emailService() {
        return new FakeEmailService();
    }
}
