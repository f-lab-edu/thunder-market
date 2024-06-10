package com.github.thundermarket.thundermarket;

import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductServiceTest {

    private final ProductService productService = new ProductService();

    @BeforeEach
    void setUp() {
        productService.clearAll();
    }

    @Test
    public void 상품0개_상품목록_조회() {
        List<Product> productList = productService.getProductList();

        Assertions.assertThat(productList.isEmpty()).isEqualTo(true);
    }

    @Test
    public void 상품1개_상품목록_조회() {
        String name = "아이폰11";
        int price = 200000;
        String status = "판매중";
        productService.addProduct(name, price, status);

        List<Product> productList = productService.getProductList();

        Assertions.assertThat(productList.getFirst().getName()).isEqualTo(name);
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
