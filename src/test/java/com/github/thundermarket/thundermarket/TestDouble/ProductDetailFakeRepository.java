package com.github.thundermarket.thundermarket.TestDouble;

import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailFakeRepository implements ProductDetailRepository {

    private final List<ProductDetail> productDetails = new ArrayList<>();

    @Override
    public ProductDetail findById(Long productId) {
        return null;
    }

    @Override
    public ProductDetail save(ProductDetail productDetail) {
        productDetails.add(productDetail);
        return productDetail;
    }
}

