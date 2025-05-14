package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.StoreRequestReqDto;
import com.example.demo.dto.StoreRequestResDto;

public interface StoreRequestService {
    StoreRequestResDto create(StoreRequestReqDto dto);
    StoreRequestResDto update(Long id, StoreRequestReqDto dto);
    void delete(Long id);
    StoreRequestResDto findById(Long id);
    List<StoreRequestResDto> findAll();
    
} 
