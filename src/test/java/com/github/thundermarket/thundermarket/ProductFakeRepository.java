package com.github.thundermarket.thundermarket;

import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductFakeRepository implements ProductRepository {

    private final List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product save(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public Product update(Product updatedProduct) {
        products.set(0, updatedProduct);
        return updatedProduct;
    }

    @Override
    public void delete(Long id) {
        products.remove(id.intValue());
    }
}
