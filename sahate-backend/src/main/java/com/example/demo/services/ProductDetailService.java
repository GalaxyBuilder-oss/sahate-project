// ProductDetailService.java
package com.example.demo.services;

import com.example.demo.dto.product.ProductDetailReqDto;
import com.example.demo.dto.product.ProductDetailResDto;

import java.util.List;

public interface ProductDetailService {
    ProductDetailResDto create(ProductDetailReqDto dto);
    ProductDetailResDto update(Long id, ProductDetailReqDto dto);
    void delete(Long id);
    ProductDetailResDto findById(Long id);
    List<ProductDetailResDto> findAll();
}
