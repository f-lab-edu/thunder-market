package com.github.thundermarket.thundermarket.domain;

import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private int price;
    private String status;

    /**
     * RestController에서 JSON 역직렬화 과정 중 ObjectMapper가 리플랙션을 사용하여 객체를 생성하기 위해 기본 생성자가 필요함
     */
    public Product() {
    }

    public Product(Long id, String name, int price, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(name, product.name) && Objects.equals(status, product.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, status);
    }
}
