package com.likelion.zeroMarket.dto;

import com.likelion.zeroMarket.domain.Sell;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellCreateRequestDto {
    private String name;
    private String category;
    private int amount;

    public static SellCreateRequestDto from(Sell sell){
        return SellCreateRequestDto.builder()
                .name(sell.getName())
                .category(sell.getCategory())
                .amount(sell.getAmount())
                .build();
    }
}
