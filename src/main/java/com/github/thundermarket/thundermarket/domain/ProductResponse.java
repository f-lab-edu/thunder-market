package com.github.thundermarket.thundermarket.domain;

import java.util.Objects;

public class ProductResponse {

    private Product product;
    private ProductDetail productDetail;

    private ProductResponse(Product product, ProductDetail productDetail) {
        this.product = product;
        this.productDetail = productDetail;
    }

    public ProductResponse() {
    }

    public Product getProduct() {
        return product;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public static ProductResponse of(Product product, ProductDetail productDetail) {
        return new ProductResponse(product, productDetail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponse that = (ProductResponse) o;
        return Objects.equals(product, that.product) && Objects.equals(productDetail, that.productDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, productDetail);
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "product=" + product +
                ", productDetail=" + productDetail +
                '}';
    }
}
