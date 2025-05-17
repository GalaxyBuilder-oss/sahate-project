package com.example.demo.services;

import com.example.demo.dto.product.ProductReviewReqDto;
import com.example.demo.dto.product.ProductReviewResDto;

import java.util.List;

public interface ProductReviewService {
    ProductReviewResDto create(ProductReviewReqDto dto);
    ProductReviewResDto update(Long id, ProductReviewReqDto dto);
    void delete(Long id);
    ProductReviewResDto findById(Long id);
    List<ProductReviewResDto> findAll();
}
