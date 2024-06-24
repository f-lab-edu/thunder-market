package com.github.thundermarket.thundermarket.controller;

import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.domain.ProductDetailResponse;
import com.github.thundermarket.thundermarket.service.ProductDetailService;
import com.github.thundermarket.thundermarket.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductDetailService productDetailService;

    public ProductController(ProductService productService, ProductDetailService productDetailService) {
        this.productService = productService;
        this.productDetailService = productDetailService;
    }

    @GetMapping("/api/v1/products")
    public ResponseEntity<List<Product>> products() {
        return new ResponseEntity<>(productService.products(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/products/{id}")
    public ResponseEntity<?> getProductDetail(@PathVariable("id") Long id) {
        ProductDetailResponse productDetailResponse = productDetailService.getProductDetail(id);
        return new ResponseEntity<>(productDetailResponse, HttpStatus.OK);
    }
}
