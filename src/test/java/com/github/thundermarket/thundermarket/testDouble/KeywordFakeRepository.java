package com.github.thundermarket.thundermarket.testDouble;

import com.github.thundermarket.thundermarket.domain.Keyword;
import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.repository.KeywordRepository;
import com.github.thundermarket.thundermarket.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class KeywordFakeRepository implements KeywordRepository {

    private final Map<Long, Keyword> inMemoryStore = new HashMap<>();
    private static long id = 1L;


    @Override
    public <S extends Keyword> S save(S entity) {
        inMemoryStore.put(id, entity);
        return entity;
    }

    @Override
    public <S extends Keyword> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Keyword> findById(Long aLong) {
        for (Long l : inMemoryStore.keySet()) {
            if (Objects.equals(l, aLong)) {
                return Optional.of(inMemoryStore.get(l));
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    public List<Keyword> findAll() {
        return new ArrayList<>(inMemoryStore.values());
    }

    @Override
    public Iterable<Keyword> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        inMemoryStore.remove(aLong);
    }

    @Override
    public void delete(Keyword entity) {
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Keyword> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Keyword> findAllByUserId(Long userId) {
        return new ArrayList<>(inMemoryStore.values());
    }
}
