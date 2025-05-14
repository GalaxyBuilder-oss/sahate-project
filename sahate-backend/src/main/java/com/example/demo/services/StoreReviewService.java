package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.StoreReviewReqDto;
import com.example.demo.dto.StoreReviewResDto;

public interface StoreReviewService {
    StoreReviewResDto create(StoreReviewReqDto dto);
    StoreReviewResDto update(Long id, StoreReviewReqDto dto);
    void delete(Long id);
    StoreReviewResDto findById(Long id);
    List<StoreReviewResDto> findAll();
    
} 
