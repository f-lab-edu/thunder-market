package com.github.thundermarket.thundermarket.dto;

import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.domain.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductRequest {

    private Product product;
    private ProductDetail productDetail;

    public Product toProduct() {
        return Product.builder()
                .title(product.getTitle())
                .name(product.getName())
                .price(product.getPrice())
                .status(product.getStatus())
                .userId(product.getUserId())
                .build();
    }

    public ProductDetail toProductDetail() {
        return ProductDetail.builder()
                .color(productDetail.getColor())
                .productCondition(productDetail.getProductCondition())
                .batteryCondition(productDetail.getBatteryCondition())
                .cameraCondition(productDetail.getCameraCondition())
                .accessories(productDetail.getAccessories())
                .purchaseDate(productDetail.getPurchaseDate())
                .warrantyDuration(productDetail.getWarrantyDuration())
                .tradeLocation(productDetail.getTradeLocation())
                .deliveryFee(productDetail.getDeliveryFee())
                .build();
    }
}
