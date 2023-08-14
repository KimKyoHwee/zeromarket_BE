package com.likelion.zeroMarket.domain;

import com.likelion.zeroMarket.dto.ProductCreateRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

    @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
    //FetchType.LAZY는 지연로딩. 즉시로딩인 EAGER는 예상치 못한 쿼리문 나갈수 있음
    @JoinColumn(name="store_id")
    private Store store;

    @Column
    private String category;

    @Column
    private String name;

    @Column
    private int stockQuantity;

    //TODO: endTime지나면 상품 DB에서 자동삭제
    //static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond)
    @Column
    private LocalDateTime endTime;

    @Column
    private int culPrice;

    @Column
    private int salePrice;

    @Column
    private String picture;

    public static Product from(ProductCreateRequestDto productDto){
        return Product.builder()
                .category(productDto.getCategory())
                .name(productDto.getName())
                .stockQuantity(productDto.getStockQuantity())
                .endTime(productDto.getEndTime())
                .culPrice(productDto.getCulPrice())
                .salePrice(productDto.getSalePrice())
                .picture(productDto.getPicture())
                .build();
    }

}
