package com.github.thundermarket.thundermarket.domain;

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
}
