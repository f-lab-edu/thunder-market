package com.github.thundermarket.thundermarket.TestDouble;

import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;

public class ProductDetailRepositoryStub implements ProductDetailRepository {

    @Override
    public ProductDetail findById(Long productId) {
        if (productId == 0) {
            return null;
        }
        return new ProductDetail(
                "white",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                0,
                ""
        );
    }

    @Override
    public ProductDetail save(ProductDetail productDetail) {
        return null;
    }
}
