package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.User;

public interface UserRepository {
    public User save(User user);
    public User findByUserIdAndPassword(String userId, String password);
}