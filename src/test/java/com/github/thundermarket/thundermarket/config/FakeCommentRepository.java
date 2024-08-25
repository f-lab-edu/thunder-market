package com.github.thundermarket.thundermarket.config;

import com.github.thundermarket.thundermarket.domain.Comment;
import com.github.thundermarket.thundermarket.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeCommentRepository implements CommentRepository {

    private List<Comment> comments = new ArrayList<>();

    @Override
    public List<Comment> findCommentsByProductId(Long productId) {
        return comments.stream()
                .filter(p -> p.getProductId().equals(productId))
                .toList();
    }

    @Override
    public <S extends Comment> S save(S entity) {
        comments.add(entity);
        return entity;
    }

    @Override
    public <S extends Comment> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Comment> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Comment> findAll() {
        return null;
    }

    @Override
    public Iterable<Comment> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return comments.size();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Comment entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Comment> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
