package com.example.demo.services;

import com.example.demo.dto.SizeReqDto;
import com.example.demo.dto.SizeResDto;

import java.util.List;

public interface SizeService {
    SizeResDto create(SizeReqDto dto);
    SizeResDto update(Long id, SizeReqDto dto);
    void delete(Long id);
    SizeResDto findById(Long id);
    List<SizeResDto> findAll();
}
