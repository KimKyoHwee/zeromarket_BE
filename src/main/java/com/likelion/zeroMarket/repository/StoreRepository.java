package com.likelion.zeroMarket.repository;

import com.likelion.zeroMarket.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findAllByAddressAndCategory(String address, String category);
    List<Store> findAllByAddress(String address);
}
