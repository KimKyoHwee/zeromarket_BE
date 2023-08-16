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
public class StoreLocationDto {
    private String name;
    private String address;
//    private Double latitude;
//    private Double longitude;


    public static StoreLocationDto from(Store store){
        return StoreLocationDto.builder()
                .name(store.getName())
                .address(store.getAddress())
//                .latitude(store.getLatitude())
//                .longitude(store.getLongitude())
                .build();
    }
}
