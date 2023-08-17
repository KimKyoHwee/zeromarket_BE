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
public class ProductSearchDto {
    private String name;
    private String picture;
    private String category;
    private int stockQuantity;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    private int salePrice;
    private int culPrice;
    private String storeName;
    private String address;


    public static ProductSearchDto from(Product product, Store store){
        return ProductSearchDto.builder()
                .name(product.getName())
                .picture(product.getPicture())
                .category(product.getCategory())
                .stockQuantity(product.getStockQuantity())
                .endTime(product.getEndTime())
                .salePrice(product.getSalePrice())
                .culPrice(product.getCulPrice())
                .storeName(store.getName())
                .address(store.getAddress())
                .build();
    }
}
