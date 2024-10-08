package com.github.thundermarket.thundermarket.config;

import com.github.thundermarket.thundermarket.constant.ProductStatus;
import com.github.thundermarket.thundermarket.domain.Product;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class PaginatedProductFakeRepository extends ProductFakeRepository {

    private final List<Product> products = new ArrayList<>();

    public PaginatedProductFakeRepository() {
        initTestData();
    }

    private void initTestData() {
        // 테스트용 데이터 300개 추가
        for (int i = 0; i < 300; i++) {
            products.add(Product.builder()
                    .id((long) i)
                    .name("iPhone13")
                    .price(300000)
                    .status(ProductStatus.AVAILABLE)
                    .userId(1L)
                    .build());
        }
    }

    @Override
    public List<Product> findByIdGreaterThanOrderByIdDesc(Long cursorId, Pageable pageable) {
        List<Product> result = new ArrayList<>();
        long effectiveCursorId = (cursorId == null) ? 0 : cursorId;

        for (Product product : products) {
            if (product.getId() > effectiveCursorId) {
                result.addFirst(product);
                if (result.size() == pageable.getPageSize()) {
                    break;  // 원하는 개수만큼 찾았으면 반복 중단
                }
            }
        }

        return result;
    }
}
