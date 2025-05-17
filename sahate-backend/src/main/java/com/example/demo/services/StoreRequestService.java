package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.store.StoreRequestReqDto;
import com.example.demo.dto.store.StoreRequestResDto;


public interface StoreRequestService {
    StoreRequestResDto create(StoreRequestReqDto dto);
    void delete(Long id);
    StoreRequestResDto findById(Long id);
    List<StoreRequestResDto> findAll();
    StoreRequestResDto updateStatus(Long id, String status);

    
} 
