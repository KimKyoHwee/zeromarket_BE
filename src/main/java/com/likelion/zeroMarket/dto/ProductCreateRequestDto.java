package com.likelion.zeroMarket.dto;

import com.likelion.zeroMarket.domain.Product;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCreateRequestDto {
    private String name;
    private String picture;
    private String category;
    private int stockQuantity;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    private int salePrice;
    private int culPrice;

    public static ProductCreateRequestDto from(Product product){
        return ProductCreateRequestDto.builder()
                .name(product.getName())
                .picture(product.getPicture())
                .category(product.getCategory())
                .stockQuantity(product.getStockQuantity())
                .endTime(product.getEndTime())
                .salePrice(product.getSalePrice())
                .culPrice(product.getCulPrice())
                .build();
    }
}
