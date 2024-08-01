package com.github.thundermarket.thundermarket.testDouble;

import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserFakeRepository implements UserRepository {

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

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<User> findById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        throw new UnsupportedOperationException();
    }
}
