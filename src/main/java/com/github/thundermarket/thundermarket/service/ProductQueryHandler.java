package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.*;
import com.github.thundermarket.thundermarket.dto.ProductDetailResponse;
import com.github.thundermarket.thundermarket.dto.ProductFilterRequest;
import com.github.thundermarket.thundermarket.dto.ProductsResponse;
import com.github.thundermarket.thundermarket.exception.ResourceNotFoundException;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;
import com.github.thundermarket.thundermarket.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryHandler {

    public static final int minPaginationLimit = 1;
    public static final int maxPaginationLimit = 100;
    public static final int minKeywordLength = 2;

    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    public ProductsResponse products(Long cursorId, int limit) {
        long effectiveCursorId = (cursorId == null) ? 0 : cursorId;
        int effectiveLimit = Math.min(maxPaginationLimit, Math.max(minPaginationLimit, limit));

        Pageable pageable = PageRequest.of(0, effectiveLimit, Sort.by(Sort.Direction.DESC, "id"));
        List<Product> products = productRepository.findByIdGreaterThanOrderByIdDesc(effectiveCursorId, pageable);
        Long newCursorId = products.isEmpty() ? null : products.getFirst().getId();
        return ProductsResponse.of(products, newCursorId, limit);
    }

    public ProductsResponse filter(ProductFilterRequest productFilterRequest) {
        return ProductsResponse.of(productRepository.filterByProductOptions(productFilterRequest));
    }

    public ProductsResponse searchTitleKeyword(String keyword) {
        if (keyword.length() < minKeywordLength) {
            throw new ResourceNotFoundException("Search term requires at least two words");
        }
        return ProductsResponse.of(productRepository.findByTitleContainingIgnoreCase(keyword));
    }

    public ProductsResponse salesHistory(SessionUser sessionUser) {
        return ProductsResponse.of(productRepository.findByUserId(sessionUser.getId()));
    }

    public ProductDetailResponse productDetail(Long productId) {
        ProductDetail productDetail = productDetailRepository.findByProductId(productId);
        if (productDetail == null) {
            throw new ResourceNotFoundException("Product detail not found with id: " + productId);
        }
        return ProductDetailResponse.of(productDetail);
    }
}
