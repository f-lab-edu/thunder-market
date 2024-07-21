package com.github.thundermarket.thundermarket.domain;

import java.util.Objects;

public class ProductFilterRequest {

    private String name;
    private Integer priceMin;
    private Integer priceMax;
    private String color;
    private String purchaseDateMin;
    private String purchaseDateMax;

    public ProductFilterRequest(String name, Integer priceMin, Integer priceMax, String color, String purchaseDateMin, String purchaseDateMax) {
        this.name = name;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.color = color;
        this.purchaseDateMin = purchaseDateMin;
        this.purchaseDateMax = purchaseDateMax;
    }

    public static ProductFilterRequest of(String name, Integer priceMin, Integer priceMax, String color, String purchaseDateMin, String purchaseDateMax) {
        return new ProductFilterRequest(name, priceMin, priceMax, color, purchaseDateMin, purchaseDateMax);
    }

    public String getName() {
        return name;
    }

    public Integer getPriceMin() {
        return priceMin;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public String getColor() {
        return color;
    }

    public String getPurchaseDateMin() {
        return purchaseDateMin;
    }

    public String getPurchaseDateMax() {
        return purchaseDateMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductFilterRequest that = (ProductFilterRequest) o;
        return priceMin == that.priceMin && priceMax == that.priceMax && Objects.equals(name, that.name) && Objects.equals(color, that.color) && Objects.equals(purchaseDateMin, that.purchaseDateMin) && Objects.equals(purchaseDateMax, that.purchaseDateMax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, priceMin, priceMax, color, purchaseDateMin, purchaseDateMax);
    }

    @Override
    public String toString() {
        return "ProductFilterRequest{" +
                "name='" + name + '\'' +
                ", priceMin=" + priceMin +
                ", priceMax=" + priceMax +
                ", color='" + color + '\'' +
                ", purchaseDateMin=" + purchaseDateMin +
                ", purchaseDateMax=" + purchaseDateMax +
                '}';
    }
}
