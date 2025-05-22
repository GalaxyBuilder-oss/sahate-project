package com.example.demo.services.product;

import java.util.List;

import com.example.demo.dto.product.ProductReqDto;
import com.example.demo.dto.product.ProductResDto;

public interface ProductService {
    ProductResDto create(ProductReqDto dto);

    ProductResDto update(Long id, ProductReqDto dto);

    void delete(Long id);

    ProductResDto findById(Long id);

    List<ProductResDto> findAll();
}
