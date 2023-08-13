package com.likelion.zeroMarket.repository;

import com.likelion.zeroMarket.domain.Sell;
import com.likelion.zeroMarket.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellRepository extends JpaRepository<Sell, Long> {
    public List<Sell> findAllByStore(Store store);
}
