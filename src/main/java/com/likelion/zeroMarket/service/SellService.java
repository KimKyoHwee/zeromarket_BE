package com.likelion.zeroMarket.service;

import com.likelion.zeroMarket.domain.Sell;
import com.likelion.zeroMarket.domain.Store;
import com.likelion.zeroMarket.dto.SellCreateRequestDto;
import com.likelion.zeroMarket.repository.SellRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SellService {
    private final SellRepository sellRepository;

    public List<SellCreateRequestDto> findMySell(Store store) {
        List<Sell> sellList = sellRepository.findAllByStore(store);
        List<SellCreateRequestDto> sellDtoList=new ArrayList<>();
        for(Sell sell:sellList){
            sellDtoList.add(SellCreateRequestDto.from(sell));
        }
        return sellDtoList;
    }
}
