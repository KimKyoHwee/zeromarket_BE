package com.likelion.zeroMarket.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainRequestDto {  //메인화면에서 받을 Dto
    private String address;
    private String category;
}
