package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.*;
import com.github.thundermarket.thundermarket.dto.FileUploadResult;
import com.github.thundermarket.thundermarket.dto.ProductResponse;
import com.github.thundermarket.thundermarket.repository.FileStorage;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;
import com.github.thundermarket.thundermarket.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class ProductCommandHandler {

    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final FileStorage fileStorage;

    public ProductCommandHandler(ProductRepository productRepository, ProductDetailRepository productDetailRepository, FileStorage fileStorage) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
        this.fileStorage = fileStorage;
    }

    public ProductResponse add(Product product, ProductDetail productDetail, MultipartFile video) throws IOException {
        Product savedProduct = productRepository.save(product);
        FileUploadResult fileUploadResult = fileStorage.save(video);
        ProductDetail productDetailWithVideoAndThumbnail = new ProductDetail.Builder(productDetail).withProductId(savedProduct.getId()).withVideo(fileUploadResult.getVideoFilePath()).withThumbnailFilePath(fileUploadResult.getThumbnailFilePath()).build();
        return ProductResponse.of(savedProduct, productDetailRepository.save(productDetailWithVideoAndThumbnail));
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
