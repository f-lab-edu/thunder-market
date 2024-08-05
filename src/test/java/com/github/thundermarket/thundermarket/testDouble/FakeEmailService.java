package com.github.thundermarket.thundermarket.testDouble;

import com.github.thundermarket.thundermarket.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.InputStream;

public class FakeEmailService extends EmailService {

    public FakeEmailService() {
        super(new JavaMailSender() {
            @Override
            public void send(SimpleMailMessage... simpleMessages) throws MailException {

            }

            @Override
            public MimeMessage createMimeMessage() {
                return null;
            }

            @Override
            public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
                return null;
            }

            @Override
            public void send(MimeMessage... mimeMessages) throws MailException {

            }
        });
    }
}