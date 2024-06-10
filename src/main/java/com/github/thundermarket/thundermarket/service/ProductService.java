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

    public void editProduct(String name2, int price2, String status2) {
        productList.set(0, new Product(name2, price2, status2));
    }

    public void deleteProduct(String name, int price, String status) {
        productList.remove(new Product(name, price, status));
    }
}
