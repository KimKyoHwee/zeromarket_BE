package com.likelion.zeroMarket.controller;

import com.likelion.zeroMarket.domain.Store;
import com.likelion.zeroMarket.domain.User;
import com.likelion.zeroMarket.dto.*;
import com.likelion.zeroMarket.service.MainService;
import com.likelion.zeroMarket.exception.DataNotFoundException;
import com.likelion.zeroMarket.domain.Product;
import com.likelion.zeroMarket.repository.ProductRepository;
import com.likelion.zeroMarket.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor  //final붙은놈 자동 주입
@RequestMapping("/main")  //swagger때문에 임시로 Main달아놓음
@CrossOrigin("*")
public class MainController {
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final MainService mainService;

    @GetMapping("/list")  //메인화면에서 지정한 지역과 카테고리에 맞는 물건들 List로 반환
    public ResponseEntity<?> mainDisplay(@RequestBody MainListDto mainListDto){
        String address=mainListDto.getAddress();
        String category=mainListDto.getCategory();
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
            }
            List<ProductCreateRequestDto> productList=mainService.getProductList(address, category);
            MainStoreProductListDto dtoList=new MainStoreProductListDto();
            dtoList.setProdctList(productList);
            dtoList.setStoreList(storeOptList);
            //TODO: DTO 한개 파서 물품 리스트랑 동네 위도경도랑 같이 주기
            return ResponseEntity.ok(dtoList);
        }catch(DataNotFoundException e) {
            return ResponseEntity.notFound().build();
            //.build로 내용 없이 ResponseEntity 객체 완성해서 return
        }
    }

    @GetMapping("/search")  //상품명으로 검색(
    public ResponseEntity<List<ProductCreateRequestDto>> searchDisplay(@RequestBody MainSearchDto mainSearchDto){
        String address=mainSearchDto.getAddress();
        String name=mainSearchDto.getName();
        try{
            List<Product> productList=mainService.getSearchProduct(address,name);
            List<ProductCreateRequestDto> DtoList=new ArrayList<>();
            for(Product product: productList){
                DtoList.add(ProductCreateRequestDto.from(product));
            }
            return ResponseEntity.ok(DtoList);
        }catch (DataNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
