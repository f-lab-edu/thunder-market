package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.domain.ProductFilterRequest;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll(Long cursorId, int limit);
    Product save(Product product);
    void delete(Long id);
    List<Product> filterByProductOptions(ProductFilterRequest productFilterRequest);
}
