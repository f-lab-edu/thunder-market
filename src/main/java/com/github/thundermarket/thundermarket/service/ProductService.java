package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository userRepository) {
        this.productRepository = userRepository;
    }


    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product editProduct(Long id, Product product) {
        return productRepository.update(id, product);
    }

    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }
}