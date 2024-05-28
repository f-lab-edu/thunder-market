package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User join(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public boolean checkPassword(User user) {
        User validUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        return validUser != null;
    }
}
