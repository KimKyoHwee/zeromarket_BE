package com.likelion.zeroMarket.dto;


import jakarta.persistence.Column;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreCreateRequestDto {  //가게 등록 DTO
    private String picture;
    private String name;
    private String explanation;
    private Double latitude;
    private Double longitude;
    private String address;  // "~~동"
    private String bank;
    private String account;
}
