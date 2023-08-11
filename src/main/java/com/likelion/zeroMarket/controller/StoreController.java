package com.likelion.zeroMarket.controller;

import com.likelion.zeroMarket.dto.StoreInfoDto;
import com.likelion.zeroMarket.exception.DataNotFoundException;
import com.likelion.zeroMarket.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
@Tag(name="store", description = "가게 관련 api")
public class StoreController {
    private final StoreService storeService;

    @Operation(summary = "내 가게 페이지", description = "로그인했을 때 반환된 userId로 유저의 가게 정보 가져오기")
    @ApiResponse(responseCode = "200", description = "가게 정보 불러오기 성공")
    @GetMapping("/{userId}")
    public ResponseEntity<StoreInfoDto> getStoreInfo(@PathVariable Long userId){
        try{
            StoreInfoDto storeInfoDto= storeService.getStoreInfo(userId);
            return ResponseEntity.ok(storeInfoDto);
        }catch(DataNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }





}
