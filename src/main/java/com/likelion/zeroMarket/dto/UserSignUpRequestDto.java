package com.likelion.zeroMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequestDto {  //회원가입 DTO
    private String id;
    private String password;
//    private String bank;
//    private String account;
    private String nickname;
}
