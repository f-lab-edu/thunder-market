package com.github.thundermarket.thundermarket.domain;

import com.github.thundermarket.thundermarket.constant.ProductStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("products")
@Getter
@NoArgsConstructor // RestController에서 JSON 역직렬화 과정 중 ObjectMapper가 리플랙션을 사용하여 객체를 생성하기 위해 기본 생성자가 필요함
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
public class Product {

    @Id
    private Long id;
    private String title;
    private String name;
    private int price;
    private ProductStatus status;
    private Long userId;

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
