package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private static final Map<Long, User> inMemoryUserStore = new HashMap<>();
    private static long id = 1L;

    @Override
    public User save(User user) {
        user.setId(id++);

        inMemoryUserStore.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(inMemoryUserStore.values());
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        for (User user : inMemoryUserStore.values()) {
            if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void deleteAll() {
        inMemoryUserStore.clear();
    }
}
