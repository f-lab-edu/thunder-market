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
    private String videoFilePath;
    private String thumbnailFilePath;

    public ProductDetail() {
    }

    public Long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public String getProductCondition() {
        return productCondition;
    }

    public String getBatteryCondition() {
        return batteryCondition;
    }

    public String getCameraCondition() {
        return cameraCondition;
    }

    public String getAccessories() {
        return accessories;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getWarrantyDuration() {
        return warrantyDuration;
    }

    public String getTradeLocation() {
        return tradeLocation;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public String getVideoFilePath() {
        return videoFilePath;
    }

    public String getThumbnailFilePath() {
        return thumbnailFilePath;
    }

    private ProductDetail(Builder builder) {
        this.id = builder.id;
        this.color = builder.color;
        this.productCondition = builder.productCondition;
        this.batteryCondition = builder.batteryCondition;
        this.cameraCondition = builder.cameraCondition;
        this.accessories = builder.accessories;
        this.purchaseDate = builder.purchaseDate;
        this.warrantyDuration = builder.warrantyDuration;
        this.tradeLocation = builder.tradeLocation;
        this.deliveryFee = builder.deliveryFee;
        this.videoFilePath = builder.videoFilePath;
        this.thumbnailFilePath = builder.thumbnailFilePath;
    }

    public ProductDetail(String color, String productCondition, String batteryCondition, String cameraCondition, String accessories, String purchaseDate, String warrantyDuration, String tradeLocation, int deliveryFee, String videoFilePath, String thumbnailFilePath) {
        this.color = color;
        this.productCondition = productCondition;
        this.batteryCondition = batteryCondition;
        this.cameraCondition = cameraCondition;
        this.accessories = accessories;
        this.purchaseDate = purchaseDate;
        this.warrantyDuration = warrantyDuration;
        this.tradeLocation = tradeLocation;
        this.deliveryFee = deliveryFee;
        this.videoFilePath = videoFilePath;
        this.thumbnailFilePath = thumbnailFilePath;
    }

    public static class Builder {
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
        private String videoFilePath;
        private String thumbnailFilePath;

        public Builder() {}

        public Builder(ProductDetail productDetail) {
            this.id = productDetail.id;
            this.color = productDetail.color;
            this.productCondition = productDetail.productCondition;
            this.batteryCondition = productDetail.batteryCondition;
            this.cameraCondition = productDetail.cameraCondition;
            this.accessories = productDetail.accessories;
            this.purchaseDate = productDetail.purchaseDate;
            this.warrantyDuration = productDetail.warrantyDuration;
            this.tradeLocation = productDetail.tradeLocation;
            this.deliveryFee = productDetail.deliveryFee;
            this.videoFilePath = productDetail.videoFilePath;
            this.thumbnailFilePath = productDetail.thumbnailFilePath;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public Builder withProductCondition(String productCondition) {
            this.productCondition = productCondition;
            return this;
        }

        public Builder withBatteryCondition(String batteryCondition) {
            this.batteryCondition = batteryCondition;
            return this;
        }

        public Builder withCameraCondition(String cameraCondition) {
            this.cameraCondition = cameraCondition;
            return this;
        }

        public Builder withAccessories(String accessories) {
            this.accessories = accessories;
            return this;
        }

        public Builder withPurchaseDate(String purchaseDate) {
            this.purchaseDate = purchaseDate;
            return this;
        }

        public Builder withWarrantyDuration(String warrantyDuration) {
            this.warrantyDuration = warrantyDuration;
            return this;
        }

        public Builder withTradeLocation(String tradeLocation) {
            this.tradeLocation = tradeLocation;
            return this;
        }

        public Builder withDeliveryFee(int deliveryFee) {
            this.deliveryFee = deliveryFee;
            return this;
        }

        public Builder withVideo(String videoFilePath) {
            this.videoFilePath = videoFilePath;
            return this;
        }

        public Builder withThumbnailFilePath(String thumbnailFilePath) {
            this.thumbnailFilePath = thumbnailFilePath;
            return this;
        }

        public ProductDetail build() {
            return new ProductDetail(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetail that = (ProductDetail) o;
        return deliveryFee == that.deliveryFee && Objects.equals(id, that.id) && Objects.equals(color, that.color) && Objects.equals(productCondition, that.productCondition) && Objects.equals(batteryCondition, that.batteryCondition) && Objects.equals(cameraCondition, that.cameraCondition) && Objects.equals(accessories, that.accessories) && Objects.equals(purchaseDate, that.purchaseDate) && Objects.equals(warrantyDuration, that.warrantyDuration) && Objects.equals(tradeLocation, that.tradeLocation) && Objects.equals(videoFilePath, that.videoFilePath) && Objects.equals(thumbnailFilePath, that.thumbnailFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, productCondition, batteryCondition, cameraCondition, accessories, purchaseDate, warrantyDuration, tradeLocation, deliveryFee, videoFilePath, thumbnailFilePath);
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", productCondition='" + productCondition + '\'' +
                ", batteryCondition='" + batteryCondition + '\'' +
                ", cameraCondition='" + cameraCondition + '\'' +
                ", accessories='" + accessories + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", warrantyDuration='" + warrantyDuration + '\'' +
                ", tradeLocation='" + tradeLocation + '\'' +
                ", deliveryFee=" + deliveryFee +
                ", videoFilePath='" + videoFilePath + '\'' +
                ", thumbnailFilePath='" + thumbnailFilePath + '\'' +
                '}';
    }
}
