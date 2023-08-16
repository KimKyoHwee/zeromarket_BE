package com.likelion.zeroMarket.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainStoreProductListDto {
    List<StoreLocationDto> storeList;
    List<ProductCreateRequestDto> prodctList;
}
