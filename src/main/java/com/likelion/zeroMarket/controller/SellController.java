package com.likelion.zeroMarket.controller;

import com.likelion.zeroMarket.dto.SellCreateRequestDto;
import com.likelion.zeroMarket.service.SellService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sell")
@Tag(name="sell", description = "팔린 물품 관련 api")
public class SellController {
    private final SellService sellService;
    @Operation(summary = "팔린 물품 등록")
    @ApiResponse(responseCode = "200", description = "판매 완료된 물품 등록 완료")
    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<?> registerSellProduct(@PathVariable("userId") Long userId,
                                                 @PathVariable("productId") Long productId,
                                                 @RequestBody SellCreateRequestDto sellDto){
        sellService.postSell(userId, productId, sellDto);
        return ResponseEntity.ok().build();
    }
}
