package com.github.thundermarket.thundermarket.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("products")
public class Product {

    @Id
    private Long id;
    private String name;
    private int price;
    private String status;

    /**
     * RestController에서 JSON 역직렬화 과정 중 ObjectMapper가 리플랙션을 사용하여 객체를 생성하기 위해 기본 생성자가 필요함
     */
    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    private Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.status = builder.status;
    }

    public static class Builder {
        private Long id;
        private String name;
        private int price;
        private String status;

        public Builder() {
        }

        public Builder(Product product) {
            this.id = product.id;
            this.name = product.name;
            this.price = product.price;
            this.status = product.status;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(status, product.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, status);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
