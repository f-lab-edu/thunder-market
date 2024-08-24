package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findCommentsByProductId(Long productId);
}
