package com.example.demo.services.store;

import com.example.demo.dto.store.SalesReportReqDto;
import com.example.demo.dto.store.SalesReportResDto;

import java.util.List;

public interface SalesReportService {
    SalesReportResDto create(SalesReportReqDto dto);
    SalesReportResDto update(Long id, SalesReportReqDto dto);
    void delete(Long id);
    SalesReportResDto findById(Long id);
    List<SalesReportResDto> findAll();
}
