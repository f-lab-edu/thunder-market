package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.User;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
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
    public User findByUserIdAndPassword(String userId, String password) {
        for (User user : inMemoryUserStore.values()) {
            if(user.getUserId().equals(userId) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void deleteAll() {
        inMemoryUserStore.clear();
    }
}
