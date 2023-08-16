package com.likelion.zeroMarket.service;

import com.likelion.zeroMarket.domain.Product;
import com.likelion.zeroMarket.dto.ProductCreateRequestDto;
import com.likelion.zeroMarket.dto.StoreLocationDto;
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


    public List<Product> getSearchProduct(String address, String name){ //검색기능
        List<Store> storeList=storeRepository.findAllByAddress(address);
        List<Product> productList=new ArrayList<>();
        for(Store store:storeList){
            productList.addAll(productRepository.findByStoreAndName(store, name));
        }
        return productList;
    }

    public List<StoreLocationDto> getStoreLocationList(String address, String category){
        List<Store> storeList=storeRepository.findAllByAddress(address);
        List<StoreLocationDto> dtoList=new ArrayList<>();
        for(Store store:storeList){
            dtoList.add(StoreLocationDto.from(store));
        }
        return dtoList;
    }

    public List<ProductCreateRequestDto> getProductList(String address, String category){
        List<Store> storeList=storeRepository.findAllByAddress(address);
        List<Product> productList=new ArrayList<>();
        for(Store store:storeList){
            productList.addAll(productRepository.findByStoreAndCategory(store, category));
        }
        List<ProductCreateRequestDto> dtoList=new ArrayList<>();
        for(Product product:productList){
            dtoList.add(ProductCreateRequestDto.from(product));
        }
        return dtoList;
    }
}
