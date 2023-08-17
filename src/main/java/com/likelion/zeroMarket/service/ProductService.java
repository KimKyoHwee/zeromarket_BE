package com.likelion.zeroMarket.service;

import com.likelion.zeroMarket.domain.Product;
import com.likelion.zeroMarket.domain.Store;
import com.likelion.zeroMarket.domain.User;
import com.likelion.zeroMarket.dto.MyProductListDto;
import com.likelion.zeroMarket.dto.ProductCreateRequestDto;
import com.likelion.zeroMarket.dto.ProductSearchDto;
import com.likelion.zeroMarket.exception.DataNotFoundException;
import com.likelion.zeroMarket.repository.ProductRepository;
import com.likelion.zeroMarket.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    //여기서 너무 자주쓰는데 그냥 userId로 해당 가게 돌려주는거 만들자
    public Store findMyStore(Long userId){
        Optional<User> user=userRepository.findById(userId);
        if(user.isPresent()){
            return user.get().getStore();
        }
        else throw new DataNotFoundException("User not found");
    }

    //자기 가게에서 자기가 고른 물품 삭제
    public void deleteProduct(Long userId, Long productId){
        Store store=findMyStore(userId);
        Optional<Product> product=
                Optional.ofNullable(productRepository.findByIdAndStore(productId, store));
        if(product.isPresent()) productRepository.delete(product.get());
        else throw new DataNotFoundException("Product not found");
    }

    public void saveProduct(Long userId, ProductCreateRequestDto productDto){ //가게에 물품등록
        //userId로 가게 찾고, 그 가게를 product 내에 저장해주자
        System.out.println("userId = " + userId);
        Optional<User> user=userRepository.findById(userId);
        System.out.println("userId = " + userId);
        if(user.isPresent()){
            Store store=user.get().getStore();
            Product product=Product.from(productDto);
            product.setStore(store);
            productRepository.save(product);
        }
        else throw new DataNotFoundException("User not found.");
    }

    public List<Product> findMyProductList(Long userId){
        Optional<User> user=userRepository.findById(userId);
        if(user.isPresent()){
            Store store=user.get().getStore();
            return productRepository.findByStore(store);
        }
        else throw new DataNotFoundException("User not found");
    }

    public ProductSearchDto findMyProduct(Store store, Long productId){  //물품 1개 id로 찾기
        Optional<Product> product=
                Optional.ofNullable(productRepository.findByIdAndStore(productId, store));
        if(product.isPresent()) return ProductSearchDto.from(product.get(), store);
        else throw new DataNotFoundException("Product not found");
    }

    public void updateProduct(Store store, Long productId, ProductCreateRequestDto productDto){
        Optional<Product> OptProduct=
                Optional.ofNullable(productRepository.findByIdAndStore(productId, store));
        if(OptProduct.isPresent()) {
            Product product=OptProduct.get();
            product.setCategory(productDto.getCategory());
            product.setName(productDto.getName());
            product.setStockQuantity(productDto.getStockQuantity());
            product.setEndTime(productDto.getEndTime());
            product.setCulPrice(productDto.getCulPrice());
            product.setSalePrice(productDto.getSalePrice());
            product.setPicture(productDto.getPicture());
            productRepository.save(product);
        }
        else throw new DataNotFoundException("Product not found");
    }
}
