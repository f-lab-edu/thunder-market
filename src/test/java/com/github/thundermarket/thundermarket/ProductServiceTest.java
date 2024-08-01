package com.github.thundermarket.thundermarket;

import com.github.thundermarket.thundermarket.TestDouble.*;
import com.github.thundermarket.thundermarket.domain.*;
import com.github.thundermarket.thundermarket.repository.FileStorage;
import com.github.thundermarket.thundermarket.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;
import java.io.FileInputStream;
import java.io.IOException;


public class ProductServiceTest {

    private final ProductFakeRepository productRepository = new ProductFakeRepository();
    private final PaginatedProductFakeRepository paginatedProductFakeRepository = new PaginatedProductFakeRepository();
    private final ProductDetailFakeRepository productDetailFakeRepository = new ProductDetailFakeRepository();
    private final FileStorage fileStorage = new FileFakeStorage();

    public Product createProduct() {
        return new Product.Builder()
                .withId(1L)
                .withTitle("아이폰 팝니다")
                .withName("iPhone12")
                .withPrice(200_000)
                .withStatus("판매중")
                .withUserId(1L)
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

    public MockMultipartFile createMockMultipartFile() throws IOException {
        return new MockMultipartFile("video", "test-video.mp4", "video/mp4", new FileInputStream(ResourceUtils.getFile("classpath:5sec.mp4")));
    }

    @Test
    public void 상품0개_상품목록_조회() {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository, fileStorage);
        ProductsResponse products = productService.products(0L, 10);

        Assertions.assertThat(products.getProducts().isEmpty()).isEqualTo(true);
    }

    @Test
    public void 상품1개_상품목록_조회() throws IOException {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository, fileStorage);
        Product product = createProduct();
        productService.add(product, createProductDetail(), createMockMultipartFile());

        ProductsResponse products = productService.products(0L, 10);

        Assertions.assertThat(products.getProducts().getFirst()).isEqualTo(product);
    }

    @Test
    public void 상품2개_상품목록_조회() throws IOException {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository, fileStorage);
        Product product = createProduct();
        productService.add(product, createProductDetail(), createMockMultipartFile());

        Long id2 = 2L;
        String name2 = "아이폰13";
        int price2 = 300000;
        String status2 = "판매중";
        Product product2 = new Product.Builder()
                .withId(id2)
                .withTitle("아이폰 팝니다")
                .withName(name2)
                .withPrice(price2)
                .withStatus(status2)
                .withUserId(2L)
                .build();
        productService.add(product2, createProductDetail(), createMockMultipartFile());

        ProductsResponse products = productService.products(0L, 10);

        Assertions.assertThat(products.getProducts().getFirst()).isEqualTo(product);
        Assertions.assertThat(products.getProducts().get(1)).isEqualTo(product2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품1개추가_다시조회() throws IOException {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository, fileStorage);
        Product product = createProduct();
        productService.add(product, createProductDetail(), createMockMultipartFile());
        ProductsResponse products = productService.products(0L, 10);
        Assertions.assertThat(products.getProducts().getFirst()).isEqualTo(product);

        Long id2 = 2L;
        String name2 = "아이폰13";
        int price2 = 300000;
        String status2 = "판매중";
        Product product2 = new Product.Builder()
                .withId(id2)
                .withTitle("아이폰 팝니다")
                .withName(name2)
                .withPrice(price2)
                .withStatus(status2)
                .withUserId(2L)
                .build();
        productService.add(product2, createProductDetail(), createMockMultipartFile());
        ProductsResponse products2 = productService.products(0L, 10);
        Assertions.assertThat(products2.getProducts().get(1)).isEqualTo(product2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품정보수정_다시조회() throws IOException {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository, fileStorage);
        Product product = createProduct();
        productService.add(product, createProductDetail(), createMockMultipartFile());
        ProductsResponse products = productService.products(0L, 10);
        Assertions.assertThat(products.getProducts().getFirst()).isEqualTo(product);

        Long originalId = 1L;
        String name2 = "아이폰13";
        int price2 = 300000;
        String status2 = "판매중";
        Product product2 = new Product.Builder()
                .withId(originalId)
                .withTitle("아이폰 팝니다")
                .withName(name2)
                .withPrice(price2)
                .withStatus(status2)
                .withUserId(2L)
                .build();
        productService.update(product2);
        ProductsResponse products2 = productService.products(0L, 10);
        Assertions.assertThat(products2.getProducts().getFirst()).isEqualTo(product2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품삭제_다시조회() throws IOException {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository, fileStorage);
        Product product = createProduct();
        productService.add(product, createProductDetail(), createMockMultipartFile());
        ProductsResponse products = productService.products(0L, 10);
        Assertions.assertThat(products.getProducts().getFirst()).isEqualTo(product);

        productService.delete(1L);
        Assertions.assertThat(products.getProducts().isEmpty()).isEqualTo(true);
    }

    @Test
    public void 상품2개_상품목록_조회_후_상품삭제_다시조회() throws IOException {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository, fileStorage);
        Product product = createProduct();
        productService.add(product, createProductDetail(), createMockMultipartFile());

        Long id2 = 2L;
        String name2 = "아이폰13";
        int price2 = 300000;
        String status2 = "판매중";
        Product product2 = new Product.Builder()
                .withId(id2)
                .withTitle("아이폰 팝니다")
                .withName(name2)
                .withPrice(price2)
                .withStatus(status2)
                .withUserId(2L)
                .build();
        productService.add(product2, createProductDetail(), createMockMultipartFile());

        ProductsResponse products = productService.products(0L, 10);

        Assertions.assertThat(products.getProducts().getFirst()).isEqualTo(product);
        Assertions.assertThat(products.getProducts().get(1)).isEqualTo(product2);

        productService.delete(1L);
        Assertions.assertThat(products.getProducts().getFirst()).isEqualTo(product2);
    }

    @Test
    public void 페이지네이션_cursor_limit_검증() {
        // 테스트 데이터를 생성해놓은 paginatedProductFakeRepository 사용
        ProductService productService = new ProductService(paginatedProductFakeRepository, productDetailFakeRepository, fileStorage);

        // cursorId 0L, limit 1 일 때 product id가 1인 것이 반환되어야 함
        ProductsResponse products = productService.products(0L, 1);
        Assertions.assertThat(products.getProducts().getFirst().getId()).isEqualTo(1);

        // cursorId 1L, limit 1 일 때 product id가 2인 것이 반환되어야 함
        products = productService.products(1L, 1);
        Assertions.assertThat(products.getProducts().getFirst().getId()).isEqualTo(2);

        // cursorId 0L, limit 100 일 때 product id가 100인 것이 반환되어야 함
        products = productService.products(0L, 100);
        Assertions.assertThat(products.getCursorId()).isEqualTo(100);

        // cursorId 0L, limit 101 일 때 limit에 사용가능한 max값이 100이기 때문에 product id가 100인 것이 반환되어야 함
        products = productService.products(0L, 101);
        Assertions.assertThat(products.getCursorId()).isEqualTo(100);

        // cursorId 0L, limit -10 일 때 limit에 사용가능한 min값이 0이기 때문에 빈 결과가 반환돼야 함
        products = productService.products(0L, -10);
        Assertions.assertThat(products.getCursorId()).isEqualTo(1);

        // cursorId 0L, limit 1 일 때 다음 커서 id는 1이어야 함
        products = productService.products(0L, 1);
        Assertions.assertThat(products.getCursorId()).isEqualTo(1);

        // cursorId 0L, limit 1 일 때 다음 커서 id는 2여야 함
        products = productService.products(1L, 1);
        Assertions.assertThat(products.getCursorId()).isEqualTo(2);
    }

    @Test
    public void 상품조회_상품옵션필터링() {
        String name = "iPhone11";
        int priceMin = 1000;
        int priceMax = 300000;
        String color = "white";
        String purchaseDateMin = "2023-01-01";
        String purchaseDateMax = "2024-07-17";
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository, fileStorage);
        ProductFilterRequest productFilterRequest = ProductFilterRequest.of(name, priceMin, priceMax, color, purchaseDateMin, purchaseDateMax);

        Product product = productService.filter(productFilterRequest).getProducts().getFirst();

        Assertions.assertThat(product.getName()).isEqualTo(name);
        Assertions.assertThat(product.getPrice()).isGreaterThanOrEqualTo(priceMin);
        Assertions.assertThat(product.getPrice()).isLessThanOrEqualTo(priceMax);
    }

    @Test
    public void 상품_제목_검색() throws IOException {
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository, fileStorage);
        Product product = createProduct();
        productService.add(product, createProductDetail(), createMockMultipartFile());

        ProductsResponse productsResponse = productService.searchTitleKeyword("팝니다");

        Assertions.assertThat(productsResponse.getProducts().getFirst().getTitle()).isEqualTo(product.getTitle());
    }

    @Test
    public void 상품_판매목록_조회() throws IOException {
        String expectedProductName = "iPhone12";
        ProductService productService = new ProductService(productRepository, productDetailFakeRepository, fileStorage);
        productService.add(createProduct(), createProductDetail(), createMockMultipartFile());
        SessionUser sessionUser = new SessionUser.Builder()
                .withId(1L)
                .withEmail("test01@email.com")
                .build();

        ProductsResponse salesHistory = productService.salesHistory(sessionUser);

        Assertions.assertThat(salesHistory.getProducts().getFirst().getName()).isEqualTo(expectedProductName);
    }
}
