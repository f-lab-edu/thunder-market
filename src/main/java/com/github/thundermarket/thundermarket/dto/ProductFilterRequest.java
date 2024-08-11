package com.github.thundermarket.thundermarket.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductFilterRequest {

    private String name;
    private Integer priceMin;
    private Integer priceMax;
    private String color;
    private String purchaseDateMin;
    private String purchaseDateMax;

    public static ProductFilterRequest of(String name, Integer priceMin, Integer priceMax, String color, String purchaseDateMin, String purchaseDateMax) {
        return new ProductFilterRequest(name, priceMin, priceMax, color, purchaseDateMin, purchaseDateMax);
    }
}
