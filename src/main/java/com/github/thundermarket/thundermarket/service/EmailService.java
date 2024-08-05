package com.github.thundermarket.thundermarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendMail(String to, String subject, String text) {
        if (to.isEmpty()) {
            throw new RuntimeException("수신자 없음");
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@thundermarket.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}