package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService {

    private final ProductDetailRepository productDetailRepository;

    @Autowired
    public ProductDetailService(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }


    public ProductDetail getProductDetail(Long productId) {
        return productDetailRepository.findById(productId);
    }
}
