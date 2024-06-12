package com.github.thundermarket.thundermarket.controller;

import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.exception.ErrorMessage;
import com.github.thundermarket.thundermarket.exception.UnauthenticatedException;
import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.service.ProductDetailService;
import com.github.thundermarket.thundermarket.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductDetailService productDetailService;

    @Autowired
    public ProductController(ProductService productService, ProductDetailService productDetailService) {
        this.productService = productService;
        this.productDetailService = productDetailService;
    }

    @GetMapping("/api/v1/products")
    public ResponseEntity<List<Product>> getProductList() {
        return new ResponseEntity<>(productService.getProductList(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/products/{id}")
    public ResponseEntity<?> getProductDetail(@PathVariable("id") Long id, HttpSession session) {
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        ProductDetail productDetail = productDetailService.getProductDetail(id);
        if (productDetail == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productDetail, HttpStatus.OK);
    }
}
