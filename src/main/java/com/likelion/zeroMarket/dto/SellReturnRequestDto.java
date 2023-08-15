package com.likelion.zeroMarket.dto;

import com.likelion.zeroMarket.domain.Sell;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellReturnRequestDto {
    private Long id;
    private String name;
    private int amount;
    private LocalDateTime sellTime;
    private String category;
    public static SellReturnRequestDto from(Sell sell){
        return SellReturnRequestDto.builder()
                .id(sell.getId())
                .name(sell.getName())
                .amount(sell.getAmount())
                .sellTime(sell.getSellTime())
                .category(sell.getCategory())
                .build();
    }
}
