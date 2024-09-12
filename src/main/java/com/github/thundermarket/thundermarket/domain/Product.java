package com.github.thundermarket.thundermarket.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.thundermarket.thundermarket.constant.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // RestController에서 JSON 역직렬화 과정 중 ObjectMapper가 리플랙션을 사용하여 객체를 생성하기 위해 기본 생성자가 필요함
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String name;
    private int price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    private Long userId;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // JSON 직렬화 할 때 포함되지 않도록 설정 (Product 만 반환하는 기능이 있기 때문)
    private ProductDetail productDetail;

    public Product update(Product product) {
        if (!Objects.equals(this.id, product.getId())) {
            throw new IllegalStateException("Cannot update product with id");
        }

        if (!Objects.equals(this.userId, product.getUserId())) {
            throw new IllegalStateException("Cannot update product with userId");
        }

        return this.toBuilder()
                .title(product.getTitle())
                .name(product.getName())
                .price(product.getPrice())
                .status(product.getStatus())
                .build();
    }
}
