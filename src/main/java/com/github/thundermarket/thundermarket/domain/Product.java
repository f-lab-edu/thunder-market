package com.github.thundermarket.thundermarket.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("products")
@Getter
@NoArgsConstructor // RestController에서 JSON 역직렬화 과정 중 ObjectMapper가 리플랙션을 사용하여 객체를 생성하기 위해 기본 생성자가 필요함
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Product {

    @Id
    private Long id;
    private String title;
    private String name;
    private int price;
    private String status;
    private Long userId;
}
