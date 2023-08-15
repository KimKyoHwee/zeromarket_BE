package com.likelion.zeroMarket.service;

import com.likelion.zeroMarket.domain.Product;
import com.likelion.zeroMarket.domain.Sell;
import com.likelion.zeroMarket.domain.Store;
import com.likelion.zeroMarket.domain.User;
import com.likelion.zeroMarket.dto.SellCreateRequestDto;
import com.likelion.zeroMarket.dto.SellReturnRequestDto;
import com.likelion.zeroMarket.exception.DataNotFoundException;
import com.likelion.zeroMarket.repository.ProductRepository;
import com.likelion.zeroMarket.repository.SellRepository;
import com.likelion.zeroMarket.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SellService {
    private final SellRepository sellRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<SellReturnRequestDto> findMySell(Store store) {
        List<Sell> sellList = sellRepository.findAllByStore(store);
        List<SellReturnRequestDto> sellDtoList=new ArrayList<>();
        for(Sell sell:sellList){
            sellDtoList.add(SellReturnRequestDto.from(sell));
        }
        return sellDtoList;
    }
    //TODO: 팔린 물품 클릭하면 SellRepository에 등록되어야함

    public void postSell(Long userId, Long productId, SellCreateRequestDto sellDto){ //팔린 물품 처리
        Optional<User> OptUser =userRepository.findById(userId);
        if(OptUser.isPresent()){
            User user=OptUser.get();
            Optional<Product> OptProduct=productRepository.findById(productId);
            if(OptProduct.isPresent()){
                Product product=OptProduct.get();
                product.setStockQuantity(product.getStockQuantity()-sellDto.getAmount());
                productRepository.save(product);
                Store store=user.getStore();
                Sell sell=Sell.from(sellDto, store);
                sellRepository.save(sell);
            }
            else throw new DataNotFoundException("Product not found");
        }
        else throw new DataNotFoundException("User not found");
    }
}
