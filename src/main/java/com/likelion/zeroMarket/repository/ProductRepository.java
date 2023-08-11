package com.likelion.zeroMarket.repository;

import com.likelion.zeroMarket.domain.Product;
import com.likelion.zeroMarket.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStoreAndCategory(Store store, String category);
    List<Product> findByStore(Store store);
    Product findByIdAndStore(Long id, Store store);
}
