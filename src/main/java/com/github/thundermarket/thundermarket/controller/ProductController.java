package com.github.thundermarket.thundermarket.controller;

import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.exception.ErrorMessage;
import com.github.thundermarket.thundermarket.exception.UnauthenticatedException;
import com.github.thundermarket.thundermarket.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/v1/products")
    public ResponseEntity<List<Product>> products() {
        return new ResponseEntity<>(productService.getProductList(), HttpStatus.OK);
    }
}
