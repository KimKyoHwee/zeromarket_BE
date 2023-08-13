package com.likelion.zeroMarket.service;

import com.likelion.zeroMarket.domain.Review;
import com.likelion.zeroMarket.domain.Store;
import com.likelion.zeroMarket.dto.ReviewRequestDto;
import com.likelion.zeroMarket.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<ReviewRequestDto> getCategoryReview(Store store, String category){
        List<Review> reviewList=reviewRepository.findAllByStoreAndCategory(store, category);
        List<ReviewRequestDto> dtoList=new ArrayList<>();
        for(Review review:reviewList){
            dtoList.add(ReviewRequestDto.from(review));
        }
        return dtoList;
    }
}
