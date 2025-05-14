package com.example.demo.services;


import java.util.List;

import com.example.demo.dto.StoreReqDto;
import com.example.demo.dto.StoreResDto;


public interface StoreService {
    StoreResDto create(StoreReqDto dto);
    StoreResDto update(Long id, StoreReqDto dto);
    void delete(Long id);   
    StoreResDto findById(Long id);
    List<StoreResDto> findAll();
}
