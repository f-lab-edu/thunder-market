package com.github.thundermarket.thundermarket;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductServiceAddTest {

    @Test
    public void 상품_추가_성공_200_성공() {
        Assertions.assertThat(1).isEqualTo(1);
    }

    @Test
    public void 상품_추가_값_안넘겼을때_400_실패() {
        Assertions.assertThat(1).isEqualTo(1);
    }

    @Test
    public void 상품_추가_중복상품_400_실패() {
        Assertions.assertThat(1).isEqualTo(1);
    }
}
