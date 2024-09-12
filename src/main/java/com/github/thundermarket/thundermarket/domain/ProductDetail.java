package com.github.thundermarket.thundermarket.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@Table(name = "productDetails")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Long productId;
    private String color;
    private String productCondition;
    private String batteryCondition;
    private String cameraCondition;
    private String accessories;
    private String purchaseDate;
    private String warrantyDuration;
    private String tradeLocation;
    private int deliveryFee;
    private String videoFilePath;
    private String thumbnailFilePath;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false) // 외래 키 설정
    private Product product;
}
