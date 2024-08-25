package com.github.thundermarket.thundermarket.unit;

import com.github.thundermarket.thundermarket.config.FakeCommentRepository;
import com.github.thundermarket.thundermarket.domain.Comment;
import com.github.thundermarket.thundermarket.repository.CommentRepository;
import com.github.thundermarket.thundermarket.service.CommentCommandHandler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentCommandHandlerTest {

    @Test
    public void 상품_댓글_등록() throws Exception {
        // given
        CommentRepository commentRepository = new FakeCommentRepository();
        CommentCommandHandler commentCommandHandler = new CommentCommandHandler(commentRepository);

        // when
        commentCommandHandler.save(Comment.builder().id(1L).text("1번 상품 삽니다").userId(1L).productId(1L).build());
        commentCommandHandler.save(Comment.builder().id(2L).text("1번 상품 삽니다").userId(2L).productId(1L).build());

        // then
        Assertions.assertThat(commentRepository.count()).isEqualTo(2);
    }
}
