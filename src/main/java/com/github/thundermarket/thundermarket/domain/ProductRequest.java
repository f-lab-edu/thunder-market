package com.github.thundermarket.thundermarket.domain;

import java.util.Objects;

public class ProductRequest {

    private Product product;
    private ProductDetail productDetail;

    public Product getProduct() {
        return product;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public Product toProduct() {
        return new Product.Builder()
                .withTitle(product.getTitle())
                .withName(product.getName())
                .withPrice(product.getPrice())
                .withStatus(product.getStatus())
                .build();
    }

    public ProductDetail toProductDetail() {
        return new ProductDetail.Builder()
                .withColor(productDetail.getColor())
                .withProductCondition(productDetail.getProductCondition())
                .withBatteryCondition(productDetail.getBatteryCondition())
                .withCameraCondition(productDetail.getCameraCondition())
                .withAccessories(productDetail.getAccessories())
                .withPurchaseDate(productDetail.getPurchaseDate())
                .withWarrantyDuration(productDetail.getWarrantyDuration())
                .withTradeLocation(productDetail.getTradeLocation())
                .withDeliveryFee(productDetail.getDeliveryFee())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRequest that = (ProductRequest) o;
        return Objects.equals(product, that.product) && Objects.equals(productDetail, that.productDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, productDetail);
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "product=" + product +
                ", productDetail=" + productDetail +
                '}';
    }
}
