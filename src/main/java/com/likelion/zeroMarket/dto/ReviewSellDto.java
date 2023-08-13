package com.likelion.zeroMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewSellDto {
    private List<ReviewRequestDto> reviewDtoList;
    private List<SellCreateRequestDto> sellDtoList;

    public static ReviewSellDto from(List<ReviewRequestDto> reviewDtoList2,
                                     List<SellCreateRequestDto> sellDtoList2){
        return ReviewSellDto.builder()
                .reviewDtoList(reviewDtoList2)
                .sellDtoList(sellDtoList2)
                .build();
    }
}
