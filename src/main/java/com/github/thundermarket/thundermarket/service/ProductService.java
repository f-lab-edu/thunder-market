package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.repository.ProductRepository;

import java.util.List;

public class ProductService {

    private final ProductRepository userRepository;

    public ProductService(ProductRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<Product> getProductList() {
        return userRepository.findAll();
    }

    public Product addProduct(Product product) {
        return userRepository.save(product);
    }

    public Product editProduct(Long id, Product product) {
        return userRepository.update(id, product);
    }

    public void deleteProduct(Long id) {
        userRepository.delete(id);
    }
}
