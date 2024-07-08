package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.ProductDetail;

public interface ProductDetailRepository {

    ProductDetail save(ProductDetail productDetail);
    ProductDetail findById(Long productId);

}
