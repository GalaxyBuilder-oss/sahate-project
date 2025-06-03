package com.example.demo.services.store;


import java.util.List;

import com.example.demo.dto.store.StoreReqDto;
import com.example.demo.dto.store.StoreResDto;


public interface StoreService {
    StoreResDto create(StoreReqDto dto);
    StoreResDto update(Long id, StoreReqDto dto);
    void delete(Long id);   
    StoreResDto findById(Long id);
    List<StoreResDto> findAll();
}
