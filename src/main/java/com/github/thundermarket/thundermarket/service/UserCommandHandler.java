package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserCommandHandler {

    private final UserRepository userRepository;

    public UserCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User join(User user) {
        return userRepository.save(user);
    }
}
