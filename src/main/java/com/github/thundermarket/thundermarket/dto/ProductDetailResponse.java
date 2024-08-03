package com.github.thundermarket.thundermarket.dto;

import com.github.thundermarket.thundermarket.domain.ProductDetail;

import java.util.Objects;

public class ProductDetailResponse {

    private final ProductDetail productDetail;

    public ProductDetailResponse(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public static ProductDetailResponse of(ProductDetail productDetail) {
        return new ProductDetailResponse(productDetail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetailResponse that = (ProductDetailResponse) o;
        return Objects.equals(productDetail, that.productDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productDetail);
    }

    @Override
    public String toString() {
        return "ProductDetailResponse{" +
                "productDetail=" + productDetail +
                '}';
    }
}
