package com.likelion.zeroMarket.domain;

import com.likelion.zeroMarket.dto.SellCreateRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)  //created 어노테이션 기능 활성화
public class Sell {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="store_id")
    private Store store;

    @Column
    private String name;

    @Column
    private String category;

    @Column
    private int amount;

    @Column
    @CreatedDate
    private LocalTime sellTime;

    public static Sell from(SellCreateRequestDto sellDto, Store requestStore){
        return Sell.builder()
                .store(requestStore)
                .name(sellDto.getName())
                .category(sellDto.getCategory())
                .amount(sellDto.getAmount())
                .build();
    }
}
