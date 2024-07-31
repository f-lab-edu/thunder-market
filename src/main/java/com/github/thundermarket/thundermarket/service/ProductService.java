package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.*;
import com.github.thundermarket.thundermarket.repository.FileStorage;
import com.github.thundermarket.thundermarket.repository.ProductDetailRepository;
import com.github.thundermarket.thundermarket.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public ProductService(ProductRepository productRepository, ProductDetailRepository productDetailRepository, FileStorage fileStorage) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
        this.fileStorage = fileStorage;
    }

    public ProductsResponse products(Long cursorId, int limit) {
        long effectiveCursorId = (cursorId == null) ? 0 : cursorId;
        int effectiveLimit = Math.min(maxPaginationLimit, Math.max(minPaginationLimit, limit));

        Pageable pageable = PageRequest.of(0, effectiveLimit, Sort.by(Sort.Direction.DESC, "id"));
        List<Product> products = productRepository.findByIdGreaterThanOrderByIdDesc(effectiveCursorId, pageable);
        Long newCursorId = products.isEmpty() ? null : products.getFirst().getId();
        return ProductsResponse.of(products, newCursorId, limit);
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

    public ProductsResponse filter(ProductFilterRequest productFilterRequest) {
        return ProductsResponse.of(productRepository.filterByProductOptions(productFilterRequest));
    }
}
