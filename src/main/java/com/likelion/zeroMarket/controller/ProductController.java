package com.likelion.zeroMarket.controller;

import com.likelion.zeroMarket.domain.Product;
import com.likelion.zeroMarket.domain.Store;
import com.likelion.zeroMarket.dto.MyProductListDto;
import com.likelion.zeroMarket.dto.ProductCreateRequestDto;
import com.likelion.zeroMarket.repository.ProductRepository;
import com.likelion.zeroMarket.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name="product", description = "물품 관련 api")
public class ProductController {
    private final ProductService productService;

    @Operation(summary="물품 삭제하기", description = "선택 삭제 기능인데, 물품의 '번호'에 있는 숫자 보내주심돼요")
    @ApiResponse(responseCode = "200", description = "물품 삭제 성공!")
    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<?> deleteMyProduct(@PathVariable Long userId,
                                             @PathVariable Long productId){
        productService.deleteProduct(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "물품 등록하기", description = "마감날짜랑 시간 형식 맞춰서 주셔야돼요!!")
    @ApiResponse(responseCode = "200", description = "물품 등록 성공!")
    @PostMapping("/{userId}")
    public ResponseEntity<?> registerProduct(@PathVariable Long userId,
                                          @RequestBody ProductCreateRequestDto productDto){
        productService.saveProduct(userId, productDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "등록된 물품 보여주기", description = "이거 List형으로 드릴게요")
    @ApiResponse(responseCode = "200", description = "물품 조회 성공!")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getProductList(@PathVariable Long userId){
        List<Product> productList=productService.findMyProductList(userId);
        List<MyProductListDto> productDtoList=new ArrayList<>();
        for(Product product:productList){
            productDtoList.add(MyProductListDto.from(product));
        }
        return ResponseEntity.ok(productDtoList);
    }
}
