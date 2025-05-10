package com.example.demo.services;

import com.example.demo.dto.ExpeditionReqDto;
import com.example.demo.dto.ExpeditionReqDto;
import com.example.demo.dto.CartResDto;
import com.example.demo.dto.ExpeditionResDto;

import java.util.List;

public interface ExpeditionService {
    ExpeditionResDto create(ExpeditionReqDto dto);
    ExpeditionResDto update(Long id, ExpeditionReqDto dto);
    void delete(Long id);
    ExpeditionResDto findById(Long id);
    List<ExpeditionResDto> findAll();
}
