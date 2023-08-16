package com.likelion.zeroMarket.service;

import com.likelion.zeroMarket.domain.User;
import com.likelion.zeroMarket.dto.StoreCreateRequestDto;
import com.likelion.zeroMarket.dto.UserSignUpRequestDto;
import com.likelion.zeroMarket.exception.DataNotFoundException;
import com.likelion.zeroMarket.repository.UserRepository;
import com.likelion.zeroMarket.domain.Store;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public User makeStore(User user, StoreCreateRequestDto storeCreateRequestDto){
        //사용자 가게 등록
        Store store=Store.from(storeCreateRequestDto);
        user.setStore(store);
        return user;
    }
    //회원가입
    public void signUp(UserSignUpRequestDto userDto, StoreCreateRequestDto storeDto){
        //이거 기능 구현 제대로 된건가..? 회원 정보 받고, 회원이 외래키로 가지고 있는 가게정보주입해주기

        User user=User.from(userDto);  //유저 정보 만들고
        userRepository.save(makeStore(user, storeDto));  //최종 유저 저장
        System.out.println("store = " + user.getStore().getName());
    }

    public User getPassword(String ids, String nickname){  //PW찾기
        /*
        Optional<User> user=this.userRepository.findById(id);
        if(user.isPresent()) return user.get();
        else throw new DataNotFoundException("question not found");
         아래가 제대로 된 Optional사용법. Optional은 일치하는 객체가 없을 때 orElseThrow한다.*/
        return userRepository.findByIdsAndNickname(ids, nickname)
                .orElseThrow(()->new DataNotFoundException("일치하는 회원 정보가 없습니다."));
    }

    public User getUserId(String ids, String password){  //로그인
        return userRepository.findByIdsAndPassword(ids, password)
                .orElseThrow(()->new DataNotFoundException("일치하는 회원 정보가 없습니다."));
    }
}
