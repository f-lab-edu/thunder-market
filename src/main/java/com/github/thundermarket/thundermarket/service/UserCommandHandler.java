package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandHandler {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User join(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User encodedUser = user.toBuilder()
                .password(encodedPassword)
                .build();
        return userRepository.save(encodedUser);
    }
}
