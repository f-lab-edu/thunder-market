package com.github.thundermarket.thundermarket.domain;

import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private int price;
    private String status;

    public Product() {
    }

    public Product(String name, int price, String status) {
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
