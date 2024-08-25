package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.Comment;
import com.github.thundermarket.thundermarket.repository.CommentRepository;

public class CommentCommandHandler {

    private final CommentRepository commentRepository;

    public CommentCommandHandler(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Long save(Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        return savedComment.getId();
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
