package com.github.thundermarket.thundermarket.controller;

import com.github.thundermarket.thundermarket.aspect.SessionUserParam;
import com.github.thundermarket.thundermarket.domain.*;
import com.github.thundermarket.thundermarket.dto.*;
import com.github.thundermarket.thundermarket.service.ProductCommandHandler;
import com.github.thundermarket.thundermarket.service.ProductQueryHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
public class ProductController {

    private final ProductQueryHandler productQueryHandler;
    private final ProductCommandHandler productCommandHandler;

    public ProductController(ProductQueryHandler productQueryHandler, ProductCommandHandler productCommandHandler) {
        this.productQueryHandler = productQueryHandler;
        this.productCommandHandler = productCommandHandler;
    }
    @GetMapping("/api/v1/products")
    public ResponseEntity<ProductsResponse> products(
            @RequestParam(name = "cursorId", defaultValue = "0") Long cursorId,
            @RequestParam(name = "limit", defaultValue = "10") int limit
    ) {
        return new ResponseEntity<>(productQueryHandler.products(cursorId, limit), HttpStatus.OK);
    }

    @GetMapping("/api/v1/products/{id}")
    public ResponseEntity<ProductDetailResponse> productDetail(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productQueryHandler.productDetail(id), HttpStatus.OK);
    }

    @PostMapping("/api/v1/products")
    public ResponseEntity<ProductResponse> add(@RequestPart("productRequest") ProductRequest productRequest,
                                               @RequestPart("video") MultipartFile video) throws IOException {
        return new ResponseEntity<>(productCommandHandler.add(productRequest.toProduct(), productRequest.toProductDetail(), video), HttpStatus.OK);
    }

    @GetMapping("/api/v1/products/filter")
    public ResponseEntity<ProductsResponse> filteredProducts(@ModelAttribute ProductFilterRequest productFilterRequest) {
        return new ResponseEntity<>(productQueryHandler.filter(productFilterRequest), HttpStatus.OK);
    }

    @GetMapping("/api/v1/products/keyword")
    public ResponseEntity<ProductsResponse> keywordProducts(@RequestParam("keyword") String keyword) {
        return new ResponseEntity<>(productQueryHandler.searchTitleKeyword(keyword), HttpStatus.OK);
    }

    @GetMapping("/api/v1/products/history/sales")
    public ResponseEntity<ProductsResponse> salesHistory(@SessionUserParam SessionUser sessionUser) {
        return new ResponseEntity<>(productQueryHandler.salesHistory(sessionUser), HttpStatus.OK);
    }
}
