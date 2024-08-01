package com.github.thundermarket.thundermarket.unit;

import com.github.thundermarket.thundermarket.testDouble.PaginatedProductFakeRepository;
import com.github.thundermarket.thundermarket.testDouble.ProductFakeRepository;
import com.github.thundermarket.thundermarket.domain.*;
import com.github.thundermarket.thundermarket.service.ProductQueryHandler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;


public class ProductQueryHandlerTest {

    private final ProductFakeRepository productRepository = new ProductFakeRepository();

    public Product createProduct(Long id, String title, String name, int price, String status, Long userId) {
        return new Product.Builder()
                .withId(id)
                .withTitle(title)
                .withName(name)
                .withPrice(price)
                .withStatus(status)
                .withUserId(userId)
                .build();
    }

    @Test
    public void 상품0개_상품목록_조회() {
        ProductQueryHandler productQueryHandler = new ProductQueryHandler(productRepository);
        ProductsResponse products = productQueryHandler.products(0L, 10);

        Assertions.assertThat(products.getProducts().isEmpty()).isEqualTo(true);
    }

    @Test
    public void 상품1개_상품목록_조회() throws IOException {
        ProductQueryHandler productQueryHandler = new ProductQueryHandler(productRepository);
        Product product = createProduct(1L, "아이폰 팝니다", "iPhone12", 200_000, "판매중", 1L);
        productRepository.save(product);

        ProductsResponse products = productQueryHandler.products(0L, 10);

        Assertions.assertThat(products.getProducts().getFirst()).isEqualTo(product);
    }

    @Test
    public void 상품2개_상품목록_조회() throws IOException {
        ProductQueryHandler productQueryHandler = new ProductQueryHandler(productRepository);
        Product product = createProduct(1L, "아이폰 팝니다", "iPhone12", 200_000, "판매중", 1L);
        productRepository.save(product);

        Product product2 = createProduct(2L, "아이폰 팝니다", "iPhone13", 300_000, "판매중", 2L);
        productRepository.save(product2);

        ProductsResponse products = productQueryHandler.products(0L, 10);

        Assertions.assertThat(products.getProducts().getFirst()).isEqualTo(product);
        Assertions.assertThat(products.getProducts().get(1)).isEqualTo(product2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품1개추가_다시조회() throws IOException {
        ProductQueryHandler productQueryHandler = new ProductQueryHandler(productRepository);
        Product product = createProduct(1L, "아이폰 팝니다", "iPhone12", 200_000, "판매중", 1L);
        productRepository.save(product);
        ProductsResponse products = productQueryHandler.products(0L, 10);
        Assertions.assertThat(products.getProducts().getFirst()).isEqualTo(product);

        Product product2 = createProduct(2L, "아이폰 팝니다", "iPhone13", 300_000, "판매중", 2L);
        productRepository.save(product2);
        ProductsResponse products2 = productQueryHandler.products(0L, 10);
        Assertions.assertThat(products2.getProducts().get(1)).isEqualTo(product2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품정보수정_다시조회() throws IOException {
        ProductQueryHandler productQueryHandler = new ProductQueryHandler(productRepository);
        Product product = createProduct(1L, "아이폰 팝니다", "iPhone12", 200_000, "판매중", 1L);
        productRepository.save(product);
        ProductsResponse products = productQueryHandler.products(0L, 10);
        Assertions.assertThat(products.getProducts().getFirst()).isEqualTo(product);

        Long originalId = 1L;
        Product product2 = createProduct(originalId, "아이폰 팝니다", "iPhone13", 300_000, "판매중", 2L);
        productRepository.save(product2);
        ProductsResponse products2 = productQueryHandler.products(0L, 10);
        Assertions.assertThat(products2.getProducts().getFirst()).isEqualTo(product2);
    }

    @Test
    public void 상품1개_상품목록_조회_후_상품삭제_다시조회() throws IOException {
        ProductQueryHandler productQueryHandler = new ProductQueryHandler(productRepository);
        Product product = createProduct(1L, "아이폰 팝니다", "iPhone12", 200_000, "판매중", 1L);
        productRepository.save(product);
        ProductsResponse products = productQueryHandler.products(0L, 10);
        Assertions.assertThat(products.getProducts().getFirst()).isEqualTo(product);

        productRepository.deleteById(1L);
        Assertions.assertThat(products.getProducts().isEmpty()).isEqualTo(true);
    }

    @Test
    public void 상품2개_상품목록_조회_후_상품삭제_다시조회() throws IOException {
        ProductQueryHandler productQueryHandler = new ProductQueryHandler(productRepository);
        Product product = createProduct(1L, "아이폰 팝니다", "iPhone12", 200_000, "판매중", 1L);
        productRepository.save(product);

        Product product2 = createProduct(2L, "아이폰 팝니다", "iPhone13", 300_000, "판매중", 2L);
        productRepository.save(product2);

        ProductsResponse products = productQueryHandler.products(0L, 10);

        Assertions.assertThat(products.getProducts().getFirst()).isEqualTo(product);
        Assertions.assertThat(products.getProducts().get(1)).isEqualTo(product2);

        productRepository.deleteById(1L);
        Assertions.assertThat(products.getProducts().getFirst()).isEqualTo(product2);
    }

    @Test
    public void 페이지네이션_cursor_limit_검증() {
        // 테스트 데이터를 생성해놓은 paginatedProductFakeRepository 사용
        ProductQueryHandler productQueryHandler = new ProductQueryHandler(new PaginatedProductFakeRepository());

        // cursorId 0L, limit 1 일 때 product id가 1인 것이 반환되어야 함
        ProductsResponse products = productQueryHandler.products(0L, 1);
        Assertions.assertThat(products.getProducts().getFirst().getId()).isEqualTo(1);

        // cursorId 1L, limit 1 일 때 product id가 2인 것이 반환되어야 함
        products = productQueryHandler.products(1L, 1);
        Assertions.assertThat(products.getProducts().getFirst().getId()).isEqualTo(2);

        // cursorId 0L, limit 100 일 때 product id가 100인 것이 반환되어야 함
        products = productQueryHandler.products(0L, 100);
        Assertions.assertThat(products.getCursorId()).isEqualTo(100);

        // cursorId 0L, limit 101 일 때 limit에 사용가능한 max값이 100이기 때문에 product id가 100인 것이 반환되어야 함
        products = productQueryHandler.products(0L, 101);
        Assertions.assertThat(products.getCursorId()).isEqualTo(100);

        // cursorId 0L, limit -10 일 때 limit에 사용가능한 min값이 0이기 때문에 빈 결과가 반환돼야 함
        products = productQueryHandler.products(0L, -10);
        Assertions.assertThat(products.getCursorId()).isEqualTo(1);

        // cursorId 0L, limit 1 일 때 다음 커서 id는 1이어야 함
        products = productQueryHandler.products(0L, 1);
        Assertions.assertThat(products.getCursorId()).isEqualTo(1);

        // cursorId 0L, limit 1 일 때 다음 커서 id는 2여야 함
        products = productQueryHandler.products(1L, 1);
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
        ProductQueryHandler productQueryHandler = new ProductQueryHandler(productRepository);
        ProductFilterRequest productFilterRequest = ProductFilterRequest.of(name, priceMin, priceMax, color, purchaseDateMin, purchaseDateMax);

        Product product = productQueryHandler.filter(productFilterRequest).getProducts().getFirst();

        Assertions.assertThat(product.getName()).isEqualTo(name);
        Assertions.assertThat(product.getPrice()).isGreaterThanOrEqualTo(priceMin);
        Assertions.assertThat(product.getPrice()).isLessThanOrEqualTo(priceMax);
    }

    @Test
    public void 상품_제목_검색() throws IOException {
        ProductQueryHandler productQueryHandler = new ProductQueryHandler(productRepository);
        Product product = createProduct(1L, "아이폰 팝니다", "iPhone12", 200_000, "판매중", 1L);
        productRepository.save(product);

        ProductsResponse productsResponse = productQueryHandler.searchTitleKeyword("팝니다");

        Assertions.assertThat(productsResponse.getProducts().getFirst().getTitle()).isEqualTo(product.getTitle());
    }

    @Test
    public void 상품_판매목록_조회() throws IOException {
        String expectedProductName = "iPhone12";
        ProductQueryHandler productQueryHandler = new ProductQueryHandler(productRepository);
        productRepository.save(createProduct(1L, "아이폰 팝니다", "iPhone12", 200_000, "판매중", 1L));
        SessionUser sessionUser = new SessionUser.Builder()
                .withId(1L)
                .withEmail("test01@email.com")
                .build();

        ProductsResponse salesHistory = productQueryHandler.salesHistory(sessionUser);

        Assertions.assertThat(salesHistory.getProducts().getFirst().getName()).isEqualTo(expectedProductName);
    }
}
