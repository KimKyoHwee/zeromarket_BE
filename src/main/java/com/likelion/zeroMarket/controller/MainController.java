package com.likelion.zeroMarket.controller;

import com.likelion.zeroMarket.domain.Store;
import com.likelion.zeroMarket.domain.User;
import com.likelion.zeroMarket.dto.*;
import com.likelion.zeroMarket.service.MainService;
import com.likelion.zeroMarket.exception.DataNotFoundException;
import com.likelion.zeroMarket.domain.Product;
import com.likelion.zeroMarket.repository.ProductRepository;
import com.likelion.zeroMarket.repository.StoreRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor  //final붙은놈 자동 주입
@RequestMapping("/main")  //swagger때문에 임시로 Main달아놓음
@CrossOrigin("*")
@Tag(name="main", description = "메인화면 api입니다.")
public class MainController {
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final MainService mainService;

    @Operation(summary = "지역별 가게들과, 카테고리별 상품들 목록")
    @ApiResponse(responseCode = "200", description = "정보 가져오기 성공!")
    @PostMapping("/{userId}/list")  //메인화면에서 지정한 지역과 카테고리에 맞는 물건들 List로 반환
    public ResponseEntity<?> mainDisplay(@PathVariable("userId") Long userId,
                                         @RequestBody MainListDto mainListDto){
        String address=mainListDto.getAddress();
        System.out.println("address = " + address);
        String category=mainListDto.getCategory();
        System.out.println("category = " + category);
    //와일드카드인 <?>를 붙임으로써 무슨 타입을 반환할지 지정하지 않는다.
        try{
            List<User> userList=mainService.getUserLocationList(address);
            List<Store> storeList=new ArrayList<>();
            for(User user:userList){
                storeList.add(user.getStore());
            }
            List<StoreLocationDto> storeOptList=new ArrayList<>();
            for(Store store:storeList){
                storeOptList.add(StoreLocationDto.from(store));
            }  //지정한 지역에 해당되는 상점 정보들 반환  완료
            List<ProductCategoryReturnDto> productList=mainService.getProductList(address, category);
            MainStoreProductListDto dtoList=new MainStoreProductListDto();
            dtoList.setProdctList(productList);
            dtoList.setStoreList(storeOptList);
            return ResponseEntity.ok(dtoList);
        }catch(DataNotFoundException e) {
            return ResponseEntity.notFound().build();
            //.build로 내용 없이 ResponseEntity 객체 완성해서 return
        }
    }

    @Operation(summary = "상품 검색", description = "지역 내에서 상품명으로 상품 검색")
    @ApiResponse(responseCode = "200", description = "상품 검색 성공!")
    @PostMapping("/{userId}/search")  //상품명으로 검색(
    public ResponseEntity<List<ProductNameSearchDto>> searchDisplay(@PathVariable("userId") Long userId,
                                                                       @RequestBody MainSearchDto mainSearchDto){
        String address=mainSearchDto.getAddress();
        String name=mainSearchDto.getName();
        try{
            List<User> userList=mainService.getUserLocationList(address);
            List<Store> storeList=new ArrayList<>();
            for(User user: userList){
                storeList.add(user.getStore());
            }
            List<Product> productList=new ArrayList<>();
            for(Store store:storeList){
                productList.addAll(productRepository.findByStoreAndName(store, name));
            }
            List<ProductNameSearchDto> dtoList=new ArrayList<>();
            for(Product product:productList){
                ProductCreateRequestDto productCreateRequestDto=ProductCreateRequestDto.from(product);
                dtoList.add(ProductNameSearchDto.from(productCreateRequestDto, product));
            }
            return ResponseEntity.ok(dtoList);
        }catch (DataNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
