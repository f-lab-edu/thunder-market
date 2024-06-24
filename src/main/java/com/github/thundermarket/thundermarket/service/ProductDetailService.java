package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.domain.ProductDetailResponse;
import com.github.thundermarket.thundermarket.exception.ResourceNotFoundException;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService {

    private final ProductDetailRepository productDetailRepository;

    public ProductDetailService(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

    public ProductDetailResponse productDetail(Long productId) {
        ProductDetail productDetail = productDetailRepository.findById(productId);
        if (productDetail == null) {
            throw new ResourceNotFoundException("Product detail not found with id: " + productId);
        }
        return productDetail.toResponse();
    }
}
