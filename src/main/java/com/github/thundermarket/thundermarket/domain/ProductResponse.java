package com.github.thundermarket.thundermarket.domain;

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
}
