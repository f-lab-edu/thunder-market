package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.Comment;
import com.github.thundermarket.thundermarket.dto.CommentRequest;
import com.github.thundermarket.thundermarket.exception.ResourceNotFoundException;
import com.github.thundermarket.thundermarket.repository.CommentRepository;
import org.apache.kafka.common.errors.AuthorizationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentCommandHandler {

    private final CommentRepository commentRepository;

    public CommentCommandHandler(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Long save(CommentRequest commentRequest, Long userId, Long productId) {
        Comment savedComment = commentRepository.save(
                Comment.builder()
                        .text(commentRequest.getText())
                        .userId(userId)
                        .productId(productId)
                        .build()
        );
        return savedComment.getId();
    }

    public Long update(Long commentId, CommentRequest commentRequest, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        if (!comment.getUserId().equals(userId)) {
            throw new AuthorizationException("You do not have permission to update this comment");
        }

        Comment savedComment = commentRepository.save(comment.toBuilder().text(commentRequest.getText()).build());
        return savedComment.getId();
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
