package com.github.thundermarket.thundermarket.controller;

import com.github.thundermarket.thundermarket.aspect.SessionUserParam;
import com.github.thundermarket.thundermarket.domain.*;
import com.github.thundermarket.thundermarket.dto.*;
import com.github.thundermarket.thundermarket.service.CommentCommandHandler;
import com.github.thundermarket.thundermarket.service.ProductCommandHandler;
import com.github.thundermarket.thundermarket.service.ProductQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductQueryHandler productQueryHandler;
    private final ProductCommandHandler productCommandHandler;
    private final CommentCommandHandler commentCommandHandler;

    @GetMapping
    public ResponseEntity<ProductsResponse> products(
            @RequestParam(name = "cursorId", defaultValue = "0") Long cursorId,
            @RequestParam(name = "limit", defaultValue = "10") int limit
    ) {
        return new ResponseEntity<>(productQueryHandler.products(cursorId, limit), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> add(@RequestPart("productRequest") ProductRequest productRequest,
                                               @RequestPart("video") MultipartFile video) throws IOException {
        return new ResponseEntity<>(productCommandHandler.add(productRequest.toProduct(), productRequest.toProductDetail(), video), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<ProductResponse> update(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productCommandHandler.update(productRequest.toProduct()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailResponse> productDetail(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productQueryHandler.productDetail(id), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<ProductsResponse> filteredProducts(@ModelAttribute ProductFilterRequest productFilterRequest) {
        return new ResponseEntity<>(productQueryHandler.filter(productFilterRequest), HttpStatus.OK);
    }

    @GetMapping("/keyword")
    public ResponseEntity<ProductsResponse> keywordProducts(@RequestParam("keyword") String keyword) {
        return new ResponseEntity<>(productQueryHandler.searchTitleKeyword(keyword), HttpStatus.OK);
    }

    @GetMapping("/history/sales")
    public ResponseEntity<ProductsResponse> salesHistory(@SessionUserParam SessionUser sessionUser) {
        return new ResponseEntity<>(productQueryHandler.salesHistory(sessionUser), HttpStatus.OK);
    }

    @PostMapping("/{product_id}/comments")
    public ResponseEntity<Long> addComment(@PathVariable("product_id") Long productId,
                                           @SessionUserParam SessionUser sessionUser,
                                           @RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(commentCommandHandler.save(commentRequest, sessionUser.getId(), productId), HttpStatus.OK);
    }

    @PutMapping("/{product_id}/comments/{comment_id}")
    public ResponseEntity<Long> updateComment(@PathVariable("comment_id") Long commentId,
                                           @SessionUserParam SessionUser sessionUser,
                                           @RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(commentCommandHandler.update(commentId, commentRequest, sessionUser.getId()), HttpStatus.OK);
    }
}
