package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserQueryHandler {

    private final UserRepository userRepository;

    public UserQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User checkCredential(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    public List<String> findEmailByUserIdIn(List<Long> userIds) {
        List<User> userEmails = userRepository.findEmailByIdIn(userIds);
        return userEmails.stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
    }
}
