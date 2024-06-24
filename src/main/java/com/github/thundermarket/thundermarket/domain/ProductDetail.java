package com.github.thundermarket.thundermarket.domain;

import java.util.Objects;

public class ProductDetail {

    private Long id;
    private String color;
    private String productCondition;
    private String batteryCondition;
    private String cameraCondition;
    private String accessories;
    private String purchaseDate;
    private String warrantyDuration;
    private String tradeLocation;
    private int deliveryFee;

    public ProductDetail() {
    }

    public ProductDetail(String color, String productCondition, String batteryCondition, String cameraCondition, String accessories, String purchaseDate, String warrantyDuration, String tradeLocation, int deliveryFee) {
        this.color = color;
        this.productCondition = productCondition;
        this.batteryCondition = batteryCondition;
        this.cameraCondition = cameraCondition;
        this.accessories = accessories;
        this.purchaseDate = purchaseDate;
        this.warrantyDuration = warrantyDuration;
        this.tradeLocation = tradeLocation;
        this.deliveryFee = deliveryFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetail that = (ProductDetail) o;
        return Objects.equals(id, that.id) && Objects.equals(color, that.color) && Objects.equals(productCondition, that.productCondition) && Objects.equals(batteryCondition, that.batteryCondition) && Objects.equals(cameraCondition, that.cameraCondition) && Objects.equals(accessories, that.accessories) && Objects.equals(purchaseDate, that.purchaseDate) && Objects.equals(warrantyDuration, that.warrantyDuration) && Objects.equals(tradeLocation, that.tradeLocation) && Objects.equals(deliveryFee, that.deliveryFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, productCondition, batteryCondition, cameraCondition, accessories, purchaseDate, warrantyDuration, tradeLocation, deliveryFee);
    }

    public ProductDetailResponse toResponse() {
        return new ProductDetailResponse(
                this.color,
                this.productCondition,
                this.batteryCondition,
                this.cameraCondition,
                this.accessories,
                this.purchaseDate,
                this.warrantyDuration,
                this.tradeLocation,
                this.deliveryFee
        );
    }
}
