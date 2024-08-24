package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.Comment;
import com.github.thundermarket.thundermarket.repository.CommentRepository;

import java.util.List;

public class CommentQueryHandler {

    private final CommentRepository commentRepository;

    public CommentQueryHandler(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findCommentsByProductId(Long productId) {
        return commentRepository.findCommentsByProductId(productId);
    }
}
