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
    private String deliveryFee;

    public ProductDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    public String getBatteryCondition() {
        return batteryCondition;
    }

    public void setBatteryCondition(String batteryCondition) {
        this.batteryCondition = batteryCondition;
    }

    public String getCameraCondition() {
        return cameraCondition;
    }

    public void setCameraCondition(String cameraCondition) {
        this.cameraCondition = cameraCondition;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getWarrantyDuration() {
        return warrantyDuration;
    }

    public void setWarrantyDuration(String warrantyDuration) {
        this.warrantyDuration = warrantyDuration;
    }

    public String getTradeLocation() {
        return tradeLocation;
    }

    public void setTradeLocation(String tradeLocation) {
        this.tradeLocation = tradeLocation;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
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
}
