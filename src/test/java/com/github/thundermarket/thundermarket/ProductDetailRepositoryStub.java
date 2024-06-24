package com.github.thundermarket.thundermarket;

import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;
import com.github.thundermarket.thundermarket.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

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
                0
        );
    }

    @Override
    public ProductDetail save(ProductDetail productDetail) {
        return null;
    }
}
