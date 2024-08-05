package com.github.thundermarket.thundermarket.testDouble;

import com.github.thundermarket.thundermarket.domain.Keyword;
import com.github.thundermarket.thundermarket.domain.User;
import com.github.thundermarket.thundermarket.dto.KeywordResponse;
import com.github.thundermarket.thundermarket.repository.KeywordRepository;
import com.github.thundermarket.thundermarket.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class KeywordFakeRepository implements KeywordRepository {

    private final List<Keyword> inMemoryStore = new ArrayList<>();


    @Override
    public <S extends Keyword> S save(S entity) {
        inMemoryStore.add(entity);
        return entity;
    }

    @Override
    public <S extends Keyword> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Keyword> findById(Long aLong) {
        for (Keyword keyword : inMemoryStore) {
            if (aLong.equals(keyword.getId())) {
                return Optional.of(keyword);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    public List<Keyword> findAll() {
        return inMemoryStore;
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
        inMemoryStore.removeIf(keyword -> keyword.getId().equals(aLong));
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
        List<Keyword> keywords = new ArrayList<>();
        for (Keyword keyword : inMemoryStore) {
            if (userId.equals(keyword.getId())) {
                keywords.add(keyword);
            }
        }
        return keywords;
    }

    @Override
    public List<Long> findUserIdsWithMatchingKeyword(String title) {
        List<Long> userIds = new ArrayList<>();
        for (Keyword keyword : inMemoryStore) {
            if (keyword.getKeyword().contains(title)) {
                userIds.add(keyword.getUserId());
            }
        }
        return userIds;
    }
}
