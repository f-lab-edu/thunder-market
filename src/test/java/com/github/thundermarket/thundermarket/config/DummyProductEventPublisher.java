package com.github.thundermarket.thundermarket.config;

import com.github.thundermarket.thundermarket.dto.ProductCreatedEvent;
import com.github.thundermarket.thundermarket.service.ProductEventPublisher;

public class DummyProductEventPublisher extends ProductEventPublisher {

    public DummyProductEventPublisher() {
        super(null, null);
    }

    @Override
    public void publishProductCreatedEvent(String productId, ProductCreatedEvent productCreatedEvent) {
    }
}
