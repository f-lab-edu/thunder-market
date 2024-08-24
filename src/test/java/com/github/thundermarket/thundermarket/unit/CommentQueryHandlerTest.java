package com.github.thundermarket.thundermarket.unit;

import com.github.thundermarket.thundermarket.config.FakeCommentRepository;
import com.github.thundermarket.thundermarket.domain.Comment;
import com.github.thundermarket.thundermarket.service.CommentQueryHandler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CommentQueryHandlerTest {

    @Test
    public void 상품_댓글_조회() throws Exception {
        // given
        Long productId = 1L;
        FakeCommentRepository commentRepository = new FakeCommentRepository();
        CommentQueryHandler commentQueryHandler = new CommentQueryHandler(commentRepository);
        commentRepository.save(Comment.builder().id(1L).text("1번 상품 삽니다").userId(1L).productId(1L).build());

        // when
        List<Comment> comments = commentQueryHandler.findCommentsByProductId(productId);

        // then
        Assertions.assertThat(comments.size()).isEqualTo(1);
    }
}
