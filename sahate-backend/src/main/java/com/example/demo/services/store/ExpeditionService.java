package com.example.demo.services.store;

import java.util.List;

import com.example.demo.dto.store.ExpeditionReqDto;
import com.example.demo.dto.store.ExpeditionResDto;

public interface ExpeditionService {
    ExpeditionResDto create(ExpeditionReqDto dto);
    ExpeditionResDto update(Long id, ExpeditionReqDto dto);
    void delete(Long id);
    ExpeditionResDto findById(Long id);
    List<ExpeditionResDto> findAll();
}
