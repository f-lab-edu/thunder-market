package com.github.thundermarket.thundermarket.service;

import com.github.thundermarket.thundermarket.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private static final List<Product> productList = new ArrayList<>();

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct(String name, int price, String status) {
        productList.add(new Product(name, price, status));
    }

    public void clearAll() {
        productList.clear();
    }
}
