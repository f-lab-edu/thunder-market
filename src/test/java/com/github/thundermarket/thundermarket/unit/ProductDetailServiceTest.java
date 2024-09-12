package com.github.thundermarket.thundermarket.unit;

import com.github.thundermarket.thundermarket.constant.ProductStatus;
import com.github.thundermarket.thundermarket.service.ProductQueryHandler;
import com.github.thundermarket.thundermarket.config.ProductDetailRepositoryStub;
import com.github.thundermarket.thundermarket.domain.ProductDetail;
import com.github.thundermarket.thundermarket.dto.ProductDetailResponse;
import com.github.thundermarket.thundermarket.exception.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.thundermarket.thundermarket.config.TestUtils.createProduct;

public class ProductDetailServiceTest {

    @Test
    public void 상품0개_상세정보_조회() {
        Long productId = 0L;
        ProductQueryHandler productDetailService = new ProductQueryHandler(null, new ProductDetailRepositoryStub());

        Assertions.assertThatThrownBy(() -> productDetailService.productDetail(productId)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void 상품1개_상세정보_조회() {
        ProductQueryHandler productDetailService = new ProductQueryHandler(null, new ProductDetailRepositoryStub());
        ProductDetail productDetail = ProductDetail.builder()
                .product(createProduct(1L, "아이폰 팝니다", "iPhone12", 200_000, ProductStatus.AVAILABLE, 1L))
                .color("white")
                .productCondition("")
                .batteryCondition("")
                .cameraCondition("")
                .accessories("")
                .purchaseDate("")
                .warrantyDuration("")
                .tradeLocation("")
                .deliveryFee(0)
                .videoFilePath("")
                .thumbnailFilePath("")
                .build();
        ProductDetailResponse expectedProductDetail = ProductDetailResponse.of(productDetail);
        Long productId = 1L;

        ProductDetailResponse productDetailResponse = productDetailService.productDetail(productId);

        Assertions.assertThat(productDetailResponse).isEqualTo(expectedProductDetail);
    }
}
