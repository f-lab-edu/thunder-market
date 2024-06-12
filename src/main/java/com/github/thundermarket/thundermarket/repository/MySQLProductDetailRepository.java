package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.ProductDetail;
import org.springframework.stereotype.Repository;

@Repository
public class MySQLProductDetailRepository implements ProductDetailRepository {
    @Override
    public ProductDetail save(ProductDetail productDetail) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProductDetail findById(Long productId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
