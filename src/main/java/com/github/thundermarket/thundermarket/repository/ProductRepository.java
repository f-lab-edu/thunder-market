package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.domain.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
    Product save(Product product);
    Product update(Product product);
    void delete(Long id);
}
