package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.domain.ProductResponse;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;
import com.github.thundermarket.thundermarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    public ProductService(ProductRepository userRepository, ProductDetailRepository productDetailRepository) {
        this.productRepository = userRepository;
        this.productDetailRepository = productDetailRepository;
    }


    public List<Product> products() {
        return productRepository.findAll();
    }

    public ProductResponse add(Product product, ProductDetail productDetail) {
        return ProductResponse.of(productRepository.save(product), productDetailRepository.save(productDetail));
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
