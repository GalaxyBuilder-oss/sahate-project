package com.example.demo.services.store;

import java.util.List;

import com.example.demo.dto.store.StoreReviewReqDto;
import com.example.demo.dto.store.StoreReviewResDto;

public interface StoreReviewService {
    StoreReviewResDto create(StoreReviewReqDto dto);
    StoreReviewResDto update(Long id, StoreReviewReqDto dto);
    void delete(Long id);
    StoreReviewResDto findById(Long id);
    List<StoreReviewResDto> findAll();
    
} 
