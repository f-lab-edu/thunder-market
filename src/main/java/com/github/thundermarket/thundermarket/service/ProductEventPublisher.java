package com.github.thundermarket.thundermarket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thundermarket.thundermarket.dto.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductEventPublisher {
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC = "product-events";

    public void publishProductCreatedEvent(String productId, ProductCreatedEvent productCreatedEvent) throws JsonProcessingException {
        kafkaTemplate.send(TOPIC, productId, objectMapper.writeValueAsString(productCreatedEvent));
    }
}