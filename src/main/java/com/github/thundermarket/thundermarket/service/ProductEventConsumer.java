package com.github.thundermarket.thundermarket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.thundermarket.thundermarket.dto.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductEventConsumer {

    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    // 스레드 풀 사이즈는 CPU 사용량은 적고 I/O 위주 작업인 메일 발송의 특성에 맞게 코어 수보다 많게 설정
    int corePoolSize = 32;
    int maximumPoolSize = 64;
    int keepAliveTime = 120;
    int capacity = 1000;
    private final ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<>(capacity), new ThreadPoolExecutor.CallerRunsPolicy());

    @KafkaListener(topics = "product-events", groupId = "thundermarket-product-group")
    public void consumeProductCreatedEvent(String message) {
        try {
            ProductCreatedEvent event = objectMapper.readValue(message, ProductCreatedEvent.class);
            List<Future<Boolean>> futures = sendEmails(event);
            for (Future<Boolean> future : futures) {
                future.get(120, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error("Error processing product created event", e);
        }
    }

    private List<Future<Boolean>> sendEmails(ProductCreatedEvent event) {
        String subject = "새 상품 등록 알림";
        String emailBody = String.format("새 상품이 등록되었습니다.\n제목: %s\n상품명: %s\n가격: %d", event.getTitle(), event.getName(), event.getPrice());

        List<Future<Boolean>> list = new ArrayList<>();
        for (String email : event.getEmails()) {
            Future<Boolean> future = executorService.submit(new MailSendCallable(email, subject, emailBody, emailService));
            list.add(future);
        }
        return list;
    }

    private static class MailSendCallable implements Callable<Boolean> {
        private final String email;
        private final String subject;
        private final String body;
        private final EmailService emailService;

        MailSendCallable(String email, String subject, String body, EmailService emailService) {
            this.email = email;
            this.subject = subject;
            this.body = body;
            this.emailService = emailService;
        }

        @Override
        public Boolean call() {
            try {
                emailService.sendMail(email, subject, body);
                return true;
            } catch (Exception e) {
                log.error("Failed to send email to {}", email, e);
                return false;
            }
        }
    }
}