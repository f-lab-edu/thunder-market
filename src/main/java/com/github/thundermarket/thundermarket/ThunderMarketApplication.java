package com.github.thundermarket.thundermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
/**
 * @see https://docs.spring.io/spring-data/jdbc/docs/current/api/org/springframework/data/jdbc/repository/config/EnableJdbcRepositories.html
 * EnableJdbcRepositories 어노테이션은 Spring Data JDBC 저장소를 활성화합니다.
 * Spring Data JDBC 규칙에 따라 정의된 인터페이스를 스캔하고, 구현체를 자동으로 생성합니다.
 */
@EnableJdbcRepositories
public class ThunderMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThunderMarketApplication.class, args);
	}

}
