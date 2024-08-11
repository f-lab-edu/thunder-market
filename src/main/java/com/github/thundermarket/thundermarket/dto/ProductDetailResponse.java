package com.github.thundermarket.thundermarket.dto;

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
public class ProductDetailResponse {

    private final ProductDetail productDetail;

    public static ProductDetailResponse of(ProductDetail productDetail) {
        return new ProductDetailResponse(productDetail);
    }
}
