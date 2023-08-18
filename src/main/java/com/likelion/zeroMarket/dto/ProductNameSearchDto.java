package com.likelion.zeroMarket.dto;

import com.likelion.zeroMarket.domain.Product;
import com.likelion.zeroMarket.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductNameSearchDto {
    private String name;
    private String picture;
    private String category;
    private int stockQuantity;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    private int salePrice;
    private int culPrice;
    private String storeName;
    private Long productId;

    public static ProductNameSearchDto from(ProductCreateRequestDto productDto, Product product){
        return ProductNameSearchDto.builder()
                .name(productDto.getName())
                .picture(productDto.getPicture())
                .category(productDto.getCategory())
                .stockQuantity(productDto.getStockQuantity())
                .endTime(productDto.getEndTime())
                .salePrice(productDto.getSalePrice())
                .culPrice(productDto.getCulPrice())
                .storeName(product.getStore().getName())
                .productId(product.getId())
                .build();
    }
}
