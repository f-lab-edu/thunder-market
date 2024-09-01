package com.github.thundermarket.thundermarket.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderStub implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return (String) rawPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}
