package com.likelion.zeroMarket.domain;

import com.likelion.zeroMarket.dto.ReviewRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동으로 증가하도록 설정
    @Column
    private Long id;

    @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

    @Column
    private String category;

    @Column
    private String content;

    public static Review from(ReviewRequestDto reviewDto, Store requestStore){
        return Review.builder()
                .store(requestStore)
                .category(reviewDto.getCategory())
                .content(reviewDto.getContent())
                .build();
    }
}
