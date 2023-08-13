package com.likelion.zeroMarket.service;

import com.likelion.zeroMarket.domain.Product;
import com.likelion.zeroMarket.repository.ProductRepository;
import com.likelion.zeroMarket.domain.Store;
import com.likelion.zeroMarket.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MainService {
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public List<Product> getProduct(String address, String category){ 
        //주어진 주소에 있는 가게들에 있는 물건들중 카테고리에 맞는것 모두 반환
        List<Store> storeList= storeRepository.findAllByAddress(address);  //일단 주소에 있는 가게 가져오고
        List<Product> productList=new ArrayList<>();
        for (Store store : storeList) {
            productList.addAll(productRepository.findByStoreAndCategory(store, category));
        }
        return productList;
    }
    
    public List<Product> getSearchProduct(String address, String name){ //검색기능
        List<Store> storeList=storeRepository.findAllByAddress(address);
        List<Product> productList=new ArrayList<>();
        for(Store store:storeList){
            productList.addAll(productRepository.findByStoreAndName(store, name));
        }
        return productList;
    }
}
