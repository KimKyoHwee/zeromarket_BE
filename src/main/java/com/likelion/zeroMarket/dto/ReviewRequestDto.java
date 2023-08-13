package com.likelion.zeroMarket.dto;

import com.likelion.zeroMarket.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequestDto {  //리뷰 생성 및 반환 dto
    private String category;
    private String content;

    public static ReviewRequestDto from(Review review){
        return ReviewRequestDto.builder()
                .category(review.getCategory())
                .content(review.getContent())
                .build();
    }
}
