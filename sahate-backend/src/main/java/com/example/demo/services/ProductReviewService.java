package com.example.demo.services;

import com.example.demo.dto.ProductReviewReqDto;
import com.example.demo.dto.ProductReviewResDto;

import java.util.List;

public interface ProductReviewService {
    ProductReviewResDto create(ProductReviewReqDto dto);
    ProductReviewResDto update(Long id, ProductReviewReqDto dto);
    void delete(Long id);
    ProductReviewResDto findById(Long id);
    List<ProductReviewResDto> findAll();
}
