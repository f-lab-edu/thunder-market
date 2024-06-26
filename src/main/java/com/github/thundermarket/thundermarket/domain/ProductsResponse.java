package com.github.thundermarket.thundermarket.domain;

import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsResponse that = (ProductsResponse) o;
        return Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(products);
    }

    @Override
    public String toString() {
        return "ProductsResponse{" +
                "products=" + products +
                '}';
    }
}
