package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.*;
import com.github.thundermarket.thundermarket.repository.FileStorage;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;
import com.github.thundermarket.thundermarket.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    public static final int minPaginationLimit = 1;
    public static final int maxPaginationLimit = 100;
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final FileStorage fileStorage;

    public ProductService(ProductRepository userRepository, ProductDetailRepository productDetailRepository, FileStorage fileStorage) {
        this.productRepository = userRepository;
        this.productDetailRepository = productDetailRepository;
        this.fileStorage = fileStorage;
    }

    public ProductsResponse products(Long cursorId, int limit) {
        long effectiveCursorId = (cursorId == null) ? 0 : cursorId;
        int effectiveLimit = Math.min(maxPaginationLimit, Math.max(minPaginationLimit, limit));

        List<Product> products = productRepository.findAll(effectiveCursorId, effectiveLimit);
        Long newCursorId = products.isEmpty() ? null : products.getFirst().getId();
        return ProductsResponse.of(products, newCursorId, limit);
    }

    public ProductResponse add(Product product, ProductDetail productDetail, MultipartFile video) throws IOException {
        FileUploadResult fileUploadResult = fileStorage.save(video);
        ProductDetail productDetailWithVideoAndThumbnail = new ProductDetail.Builder(productDetail).withVideo(fileUploadResult.getVideoFilePath()).withThumbnailFilePath(fileUploadResult.getThumbnailFilePath()).build();
        return ProductResponse.of(productRepository.save(product), productDetailRepository.save(productDetailWithVideoAndThumbnail));
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
