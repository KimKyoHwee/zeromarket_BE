package com.likelion.zeroMarket.repository;

import com.likelion.zeroMarket.domain.Review;
import com.likelion.zeroMarket.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findAllByStoreAndCategory(Store store, String category);
}
