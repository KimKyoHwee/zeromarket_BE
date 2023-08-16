package com.likelion.zeroMarket.controller;

import com.likelion.zeroMarket.domain.Store;
import com.likelion.zeroMarket.dto.MainStoreProductListDto;
import com.likelion.zeroMarket.dto.ProductCreateRequestDto;
import com.likelion.zeroMarket.dto.StoreLocationDto;
import com.likelion.zeroMarket.service.MainService;
import com.likelion.zeroMarket.exception.DataNotFoundException;
import com.likelion.zeroMarket.domain.Product;
import com.likelion.zeroMarket.repository.ProductRepository;
import com.likelion.zeroMarket.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor  //final붙은놈 자동 주입
@RequestMapping("/main")  //swagger때문에 임시로 Main달아놓음
public class MainController {
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final MainService mainService;

    @GetMapping("/{address}/{category}")  //메인화면에서 지정한 지역과 카테고리에 맞는 물건들 List로 반환
    public ResponseEntity<?> mainDisplay(@PathVariable("address") String address,
                                         @PathVariable("category") String category){
    //와일드카드인 <?>를 붙임으로써 무슨 타입을 반환할지 지정하지 않는다.
        try{
            List<StoreLocationDto> storeList=mainService.getStoreLocationList(address, category);
            List<ProductCreateRequestDto> productList=mainService.getProductList(address, category);
            MainStoreProductListDto dtoList=new MainStoreProductListDto();
            dtoList.setProdctList(productList);
            dtoList.setStoreList(storeList);
            //TODO: DTO 한개 파서 물품 리스트랑 동네 위도경도랑 같이 주기
            return ResponseEntity.ok(dtoList);
        }catch(DataNotFoundException e) {
            return ResponseEntity.notFound().build();
            //.build로 내용 없이 ResponseEntity 객체 완성해서 return
        }
    }

    @GetMapping("/{address}/{name}")  //상품명으로 검색(
    public ResponseEntity<List<ProductCreateRequestDto>> searchDisplay(@PathVariable("address") String address,
                                           @PathVariable("name") String name){
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
