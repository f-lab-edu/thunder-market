package com.github.thundermarket.thundermarket;

import com.github.thundermarket.thundermarket.TestDouble.ProductDetailFakeRepository;
import com.github.thundermarket.thundermarket.TestDouble.ProductFakeRepository;
import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductServiceTest {

    private final ProductFakeRepository productRepository = new ProductFakeRepository();
    private final ProductDetailFakeRepository productDetailFakeRepository = new ProductDetailFakeRepository();

    public Product createProduct() {
        return new Product.Builder()
                .withId(1L)
                .withName("iPhone12")
                .withPrice(200_000)
                .withStatus("판매중")
                .build();
    }

    public ProductDetail createProductDetail() {
        return new ProductDetail.Builder()
                .withId(1L)
                .withColor("white")
                .withBatteryCondition("80%")
                .withCameraCondition("good")
                .withDeliveryFee(3000)
                .build();
    }

    @Test
    public void 상품0개_상품목록_조회() {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository);
        List<Product> productList = productService.products();

        Assertions.assertThat(productList.isEmpty()).isEqualTo(true);
    }

    @Test
    public void 상품1개_상품목록_조회() {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository);
        Product product = createProduct();
        productService.add(product, createProductDetail());

        List<Product> productList = productService.products();

        Assertions.assertThat(productList.getFirst()).isEqualTo(product);
    }

    @Test
    public void 상품2개_상품목록_조회() {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository);
        Product product = createProduct();
        productService.add(product, createProductDetail());

        Long id2 = 2L;
        String name2 = "아이폰13";
        int price2 = 300000;
        String status2 = "판매중";
        Product product2 = new Product.Builder()
                .withId(id2)
                .withName(name2)
                .withPrice(price2)
                .withStatus(status2)
                .build();
        productService.add(product2, createProductDetail());

        List<Product> productList = productService.products();
        System.out.println(productList);

        Assertions.assertThat(productList.getFirst()).isEqualTo(product);
        Assertions.assertThat(productList.get(1)).isEqualTo(product2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품1개추가_다시조회() {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository);
        Product product = createProduct();
        productService.add(product, createProductDetail());
        List<Product> productList = productService.products();
        Assertions.assertThat(productList.getFirst()).isEqualTo(product);

        Long id2 = 2L;
        String name2 = "아이폰13";
        int price2 = 300000;
        String status2 = "판매중";
        Product product2 = new Product.Builder()
                .withId(id2)
                .withName(name2)
                .withPrice(price2)
                .withStatus(status2)
                .build();
        productService.add(product2, createProductDetail());
        List<Product> productList2 = productService.products();
        Assertions.assertThat(productList2.get(1)).isEqualTo(product2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품정보수정_다시조회() {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository);
        Product product = createProduct();
        productService.add(product, createProductDetail());
        List<Product> productList = productService.products();
        Assertions.assertThat(productList.getFirst()).isEqualTo(product);

        Long originalId = 1L;
        String name2 = "아이폰13";
        int price2 = 300000;
        String status2 = "판매중";
        Product product2 = new Product.Builder()
                .withId(originalId)
                .withName(name2)
                .withPrice(price2)
                .withStatus(status2)
                .build();
        productService.update(product2);
        List<Product> productList2 = productService.products();
        Assertions.assertThat(productList2.getFirst()).isEqualTo(product2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품삭제_다시조회() {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository);
        Product product = createProduct();
        productService.add(product, createProductDetail());
        List<Product> productList = productService.products();
        Assertions.assertThat(productList.getFirst()).isEqualTo(product);

        productService.delete(0L);
        Assertions.assertThat(productList.isEmpty()).isEqualTo(true);
    }

    @Test
    public void 상품2개_상품목록_조회_후_상품삭제_다시조회() {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository);
        Product product = createProduct();
        productService.add(product, createProductDetail());

        Long id2 = 2L;
        String name2 = "아이폰13";
        int price2 = 300000;
        String status2 = "판매중";
        Product product2 = new Product.Builder()
                .withId(id2)
                .withName(name2)
                .withPrice(price2)
                .withStatus(status2)
                .build();
        productService.add(product2, createProductDetail());

        List<Product> productList = productService.products();

        Assertions.assertThat(productList.getFirst()).isEqualTo(product);
        Assertions.assertThat(productList.get(1)).isEqualTo(product2);

        productService.delete(0L);
        Assertions.assertThat(productList.getFirst()).isEqualTo(product2);
    }
}
