// ProductDetailService.java
package com.example.demo.services;

import com.example.demo.dto.ProductDetailReqDto;
import com.example.demo.dto.ProductDetailResDto;

import java.util.List;

public interface ProductDetailService {
    ProductDetailResDto create(ProductDetailReqDto dto);
    ProductDetailResDto update(Long id, ProductDetailReqDto dto);
    void delete(Long id);
    ProductDetailResDto findById(Long id);
    List<ProductDetailResDto> findAll();
}
