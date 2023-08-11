package com.likelion.zeroMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserStoreDto {  //회원가입시 유저정보+가게정보 입력 DTO
    private UserSignUpRequestDto userSignUpRequestDto;
    private StoreCreateRequestDto storeCreateRequestDto;
}
