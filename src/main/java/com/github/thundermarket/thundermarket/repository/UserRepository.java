package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.User;

import java.util.List;

public interface UserRepository {
    public User save(User user);
    public List<User> findAll();
    public User findByEmailAndPassword(String email, String password);
}