package com.github.thundermarket.thundermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class ThunderMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThunderMarketApplication.class, args);
	}

}
