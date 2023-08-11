package com.likelion.zeroMarket.service;

import com.likelion.zeroMarket.domain.Store;
import com.likelion.zeroMarket.domain.User;
import com.likelion.zeroMarket.dto.StoreInfoDto;
import com.likelion.zeroMarket.exception.DataNotFoundException;
import com.likelion.zeroMarket.repository.StoreRepository;
import com.likelion.zeroMarket.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public StoreInfoDto getStoreInfo(Long userId){  //userId로 해당 user의 store 정보 찾아서 반환
        Optional<User> user=userRepository.findById(userId);
        if(user.isPresent()) {
            Store store=user.get().getStore();
            return StoreInfoDto.from(store);
        } else throw new DataNotFoundException("User not found");
    }
}
