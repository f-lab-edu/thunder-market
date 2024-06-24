package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.ProductDetail;

public interface ProductDetailRepository {

    public ProductDetail save(ProductDetail productDetail);
    public ProductDetail findById(Long productId);

}
