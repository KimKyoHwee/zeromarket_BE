package com.likelion.zeroMarket.repository;

import com.likelion.zeroMarket.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //User객체를 저장하는 리포지토리인데, PK가 String타입

    Optional<User> findByIdsAndNickname(String id, String nickname); //PW찾기용
    //Optional 형태로 받아야 서비스에서 OrElseThrow 사용 가능
    Optional<User> findByIdsAndPassword(String id, String password); //로그인용

}
