package com.example.demo.services.product;

import com.example.demo.dto.product.SizeReqDto;
import com.example.demo.dto.product.SizeResDto;

import java.util.List;

public interface SizeService {
    SizeResDto create(SizeReqDto dto);
    SizeResDto update(Long id, SizeReqDto dto);
    void delete(Long id);
    SizeResDto findById(Long id);
    List<SizeResDto> findAll();
}
