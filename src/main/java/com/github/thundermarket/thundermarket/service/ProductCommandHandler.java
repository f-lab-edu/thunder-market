package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.*;
import com.github.thundermarket.thundermarket.dto.FileUploadResult;
import com.github.thundermarket.thundermarket.dto.ProductCreatedEvent;
import com.github.thundermarket.thundermarket.dto.ProductResponse;
import com.github.thundermarket.thundermarket.repository.FileStorage;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;
import com.github.thundermarket.thundermarket.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandHandler {

    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final FileStorage fileStorage;
    private final ProductEventPublisher productEventPublisher;
    private final KeywordMatchingService keywordMatchingService;

    public ProductResponse add(Product product, ProductDetail productDetail, MultipartFile video, String email) throws IOException {
        Product savedProduct = productRepository.save(product);
        FileUploadResult fileUploadResult = fileStorage.save(video);
        ProductDetail productDetailWithVideoAndThumbnail = productDetail.toBuilder()
                .productId(savedProduct.getId())
                .videoFilePath(fileUploadResult.getVideoFilePath())
                .thumbnailFilePath(fileUploadResult.getThumbnailFilePath())
                .build();

        List<String> emails = keywordMatchingService.findEmailsByKeywordsInTitle(product.getTitle());
        productEventPublisher.publishProductCreatedEvent(product.getId().toString(),
                ProductCreatedEvent.builder()
                        .title(product.getTitle())
                        .name(product.getName())
                        .price(product.getPrice())
                        .emails(emails)
                        .build());
        return ProductResponse.of(savedProduct, productDetailRepository.save(productDetailWithVideoAndThumbnail));
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
