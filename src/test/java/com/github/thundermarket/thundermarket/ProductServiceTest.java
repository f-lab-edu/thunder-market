package com.github.thundermarket.thundermarket;

import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductServiceTest {

    private final ProductFakeRepository productRepository = new ProductFakeRepository();

    @Test
    public void 상품0개_상품목록_조회() {
        ProductService productService = new ProductService(productRepository);
        List<Product> productList = productService.getProductList();

        Assertions.assertThat(productList.isEmpty()).isEqualTo(true);
    }

    @Test
    public void 상품1개_상품목록_조회() {
        ProductService productService = new ProductService(productRepository);
        Long id = 0L;
        String name = "아이폰11";
        int price = 200000;
        String status = "판매중";
        Product product = new Product(id, name, price, status);
        productService.add(product);

        List<Product> productList = productService.getProductList();

        Assertions.assertThat(productList.getFirst()).isEqualTo(product);
    }

    @Test
    public void 상품2개_상품목록_조회() {
        ProductService productService = new ProductService(productRepository);
        Long id = 0L;
        String name = "아이폰11";
        int price = 200000;
        String status = "판매중";
        Product product = new Product(id, name, price, status);
        productService.add(product);

        Long id2 = 1L;
        String name2 = "아이폰12";
        int price2 = 300000;
        String status2 = "판매중";
        Product product2 = new Product(id2, name2, price2, status2);
        productService.add(product2);

        List<Product> productList = productService.getProductList();

        Assertions.assertThat(productList.getFirst()).isEqualTo(product);
        Assertions.assertThat(productList.get(1)).isEqualTo(product2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품1개추가_다시조회() {
        ProductService productService = new ProductService(productRepository);
        Long id = 0L;
        String name = "아이폰11";
        int price = 200000;
        String status = "판매중";
        Product product = new Product(id, name, price, status);
        productService.add(product);
        List<Product> productList = productService.getProductList();
        Assertions.assertThat(productList.getFirst()).isEqualTo(product);

        Long id2 = 1L;
        String name2 = "아이폰12";
        int price2 = 300000;
        String status2 = "판매중";
        Product product2 = new Product(id2, name2, price2, status2);
        productService.add(product2);
        List<Product> productList2 = productService.getProductList();
        Assertions.assertThat(productList2.get(1)).isEqualTo(product2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품정보수정_다시조회() {
        ProductService productService = new ProductService(productRepository);
        Long id = 0L;
        String name = "아이폰11";
        int price = 200000;
        String status = "판매중";
        Product product = new Product(id, name, price, status);
        productService.add(product);
        List<Product> productList = productService.getProductList();
        Assertions.assertThat(productList.getFirst()).isEqualTo(product);

        String name2 = "아이폰12";
        int price2 = 300000;
        String status2 = "판매중";
        Product product2 = new Product(id, name2, price2, status2);
        productService.update(product2);
        List<Product> productList2 = productService.getProductList();
        Assertions.assertThat(productList2.getFirst()).isEqualTo(product2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품삭제_다시조회() {
        ProductService productService = new ProductService(productRepository);
        Long id = 0L;
        String name = "아이폰11";
        int price = 200000;
        String status = "판매중";
        Product product = new Product(id, name, price, status);
        productService.add(product);
        List<Product> productList = productService.getProductList();
        Assertions.assertThat(productList.getFirst()).isEqualTo(product);

        productService.delete(0L);
        Assertions.assertThat(productList.isEmpty()).isEqualTo(true);
    }

    @Test
    public void 상품2개_상품목록_조회_후_상품삭제_다시조회() {
        ProductService productService = new ProductService(productRepository);
        Long id = 0L;
        String name = "아이폰11";
        int price = 200000;
        String status = "판매중";
        Product product = new Product(id, name, price, status);
        productService.add(product);

        Long id2 = 1L;
        String name2 = "아이폰12";
        int price2 = 300000;
        String status2 = "판매중";
        Product product2 = new Product(id2, name2, price2, status2);
        productService.add(product2);

        List<Product> productList = productService.getProductList();

        Assertions.assertThat(productList.getFirst()).isEqualTo(product);
        Assertions.assertThat(productList.get(1)).isEqualTo(product2);

        productService.delete(0L);
        Assertions.assertThat(productList.getFirst()).isEqualTo(product2);
    }
}
