package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryHandler {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getAuthenticatedUser(User user) {
        User savedUser = userRepository.findByEmail(user.getEmail());
        if (!passwordEncoder.matches(user.getPassword(), savedUser.getPassword())) {
            return null;
        }
        return savedUser;
    }

    public List<String> findEmailByUserIdIn(List<Long> userIds) {
        List<User> userEmails = userRepository.findEmailByIdIn(userIds);
        return userEmails.stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
    }
}
