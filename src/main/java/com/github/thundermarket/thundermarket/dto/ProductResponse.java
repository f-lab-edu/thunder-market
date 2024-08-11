package com.github.thundermarket.thundermarket.dto;

import com.github.thundermarket.thundermarket.domain.Product;
import com.github.thundermarket.thundermarket.domain.ProductDetail;
import lombok.*;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductResponse {

    private Product product;
    private ProductDetail productDetail;

    public static ProductResponse of(Product product, ProductDetail productDetail) {
        return new ProductResponse(product, productDetail);
    }
}
