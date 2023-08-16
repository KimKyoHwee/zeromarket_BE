package com.likelion.zeroMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequestDto {  //회원가입 DTO
    private String ids;
    private String password;
    private String nickname;
    private Double latitude;
    private Double longitude;
}
