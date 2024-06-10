package com.github.thundermarket.thundermarket;

import com.github.thundermarket.thundermarket.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceTest {

    @Test
    public void 상품0개_상품목록_조회() {
        List<Product> productList = new ArrayList<>(); 
        Assertions.assertThat(productList.isEmpty()).isEqualTo(true);
    }

    @Test
    public void 상품1개_상품목록_조회() {
        Assertions.assertThat(1).isEqualTo(2);
    }

    @Test
    public void 상품2개_상품목록_조회() {
        Assertions.assertThat(1).isEqualTo(2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품1개추가_다시조회() {
        Assertions.assertThat(1).isEqualTo(2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품정보수정_다시조회() {
        Assertions.assertThat(1).isEqualTo(2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품삭제_다시조회() {
        Assertions.assertThat(1).isEqualTo(2);
    }

    @Test
    public void 상품2개_상품목록_조회_후_상품삭제_다시조회() {
        Assertions.assertThat(1).isEqualTo(2);
    }
}
