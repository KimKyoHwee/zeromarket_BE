package com.likelion.zeroMarket.dto;

import com.likelion.zeroMarket.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreInfoDto {  //내 가게 정보에서 보여줄 가게정보DTO
    private String picture;
    private String name;
    private String explanation;
    private Double latitude;
    private Double longitude;
    private String bank;
    private String account;

    public static StoreInfoDto from(Store store){  //스토어 객체 받아서 내가 반환하고싶은 정보들로 만들어 반환하자
        return StoreInfoDto.builder()
                .picture(store.getPicture())
                .name(store.getName())
                .explanation(store.getExplanation())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .bank(store.getBank())
                .account(store.getAccount())
                .build();
    }
}
