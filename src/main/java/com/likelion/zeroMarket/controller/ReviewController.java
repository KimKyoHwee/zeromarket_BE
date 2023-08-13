package com.likelion.zeroMarket.controller;

import com.likelion.zeroMarket.domain.Review;
import com.likelion.zeroMarket.domain.Sell;
import com.likelion.zeroMarket.domain.Store;
import com.likelion.zeroMarket.dto.ReviewRequestDto;
import com.likelion.zeroMarket.dto.ReviewSellDto;
import com.likelion.zeroMarket.dto.SellCreateRequestDto;
import com.likelion.zeroMarket.service.ProductService;
import com.likelion.zeroMarket.service.ReviewService;
import com.likelion.zeroMarket.service.SellService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@Tag(name="review", description = "가게 리뷰 관련 api")
public class ReviewController {
    private final ReviewService reviewService;
    private final ProductService productService;
    private final SellService sellService;

    @Operation(summary="리뷰보기")
    @ApiResponse(responseCode = "200", description = "리뷰 불러오기 성공")
    @GetMapping("/{userId}/{category}")
    public ResponseEntity<?> getReview(@PathVariable Long userId, @PathVariable String category){
        //TODO: 카테고리에 맞는 리뷰와 함께 판매완료된 상품들 보내줘야함
        Store store=productService.findMyStore(userId);
        List<ReviewRequestDto> reviewList=reviewService.getCategoryReview(store, category);
        List<SellCreateRequestDto> sellList=sellService.findMySell(store);
        return ResponseEntity.ok(ReviewSellDto.from(reviewList, sellList));
    }
}
