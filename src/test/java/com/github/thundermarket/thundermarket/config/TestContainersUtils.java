package com.github.thundermarket.thundermarket.config;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

public class TestContainersUtils {

    private static final MySQLContainer<?> mysqlContainer;
    private static final GenericContainer<?> redisContainer;
    private static final KafkaContainer kafkaContainer;

    static {
        mysqlContainer = new MySQLContainer<>("mysql:8.0")
                .withDatabaseName("testdb")
                .withUsername("test")
                .withPassword("test")
                .withInitScript("schemaWithData.sql");

        redisContainer = new GenericContainer<>(DockerImageName.parse("redis:6.2-alpine"))
                .withExposedPorts(6379);

        kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));

        mysqlContainer.start();
        redisContainer.start();
        kafkaContainer.start();
    }

    public static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
    }

    public static void registerRedisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", redisContainer::getHost);
        registry.add("spring.data.redis.port", redisContainer::getFirstMappedPort);
    }

    public static void registerKafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    public static MySQLContainer<?> getMysqlContainer() {
        return mysqlContainer;
    }

    public static GenericContainer<?> getRedisContainer() {
        return redisContainer;
    }

    public static KafkaContainer getKafkaContainer() {
        return kafkaContainer;
    }

}
