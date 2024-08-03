package com.github.thundermarket.thundermarket.testDouble;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thundermarket.thundermarket.dto.ProductCreatedEvent;
import com.github.thundermarket.thundermarket.service.ProductEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;

public class DummyProductEventPublisher extends ProductEventPublisher {

    public DummyProductEventPublisher() {
        super(null, null);
    }

    @Override
    public void publishProductCreatedEvent(String productId, ProductCreatedEvent productCreatedEvent) {
    }
}
