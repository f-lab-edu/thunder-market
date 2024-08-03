package com.github.thundermarket.thundermarket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thundermarket.thundermarket.dto.ProductCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductEventConsumer {

    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public ProductEventConsumer(ObjectMapper objectMapper, EmailService emailService) {
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "product-events", groupId = "thundermarket-product-group")
    public void consumeProductCreatedEvent(String message) {
        try {
            ProductCreatedEvent event = objectMapper.readValue(message, ProductCreatedEvent.class);

            String subject = "새 상품 등록 알림";
            String emailBody = String.format("새 상품이 등록되었습니다.\n제목: %s\n상품명: %s\n가격: %d",
                    event.getTitle(), event.getName(), event.getPrice());

            emailService.sendMail(event.getEmail(), subject, emailBody);
        } catch (Exception e) {
            log.error("Error processing product created event", e);
        }
    }
}