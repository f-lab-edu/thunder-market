package com.github.thundermarket.thundermarket.dto;

import com.github.thundermarket.thundermarket.domain.Product;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductsResponse {

    private final List<Product> products;
    private final Long cursorId;
    private final int limit;

    public ProductsResponse(List<Product> products) {
        this(products, null, 0);
    }

    public static ProductsResponse of(List<Product> products) {
        return new ProductsResponse(products);
    }

    public static ProductsResponse of(List<Product> products, Long newCursorId, int limit) {
        return new ProductsResponse(products, newCursorId, limit);
    }
}
