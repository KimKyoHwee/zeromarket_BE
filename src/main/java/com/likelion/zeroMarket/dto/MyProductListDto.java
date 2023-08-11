package com.likelion.zeroMarket.dto;

import com.likelion.zeroMarket.domain.Product;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyProductListDto {
    private Long id;
    private String picture;
    private String name;
    private String category;
    private int stockQuantity;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    private int salePrice;
    private int culPrice;

    public static MyProductListDto from(Product product){
        return MyProductListDto.builder()
                .id(product.getId())
                .picture(product.getPicture())
                .name(product.getName())
                .category(product.getCategory())
                .stockQuantity(product.getStockQuantity())
                .endTime(product.getEndTime())
                .salePrice(product.getSalePrice())
                .culPrice(product.getCulPrice())
                .build();
    }
}
