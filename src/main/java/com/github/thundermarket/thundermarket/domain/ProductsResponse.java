package com.github.thundermarket.thundermarket.domain;

import java.util.List;

public class ProductsResponse {

    private final List<Product> products;

    private ProductsResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public static ProductsResponse of(List<Product> products) {
        return new ProductsResponse(products);
    }

    @Override
    public String toString() {
        return "ProductsResponse{" +
                "products=" + products +
                '}';
    }
}
