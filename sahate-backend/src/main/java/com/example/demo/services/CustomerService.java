package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.CustomerReqDto;
import com.example.demo.dto.CustomerResDto;

public interface CustomerService {
    CustomerResDto create(CustomerReqDto dto);
    CustomerResDto update(Long id, CustomerReqDto dto);
    void delete(Long id);
    CustomerResDto findById(Long id);
    List<CustomerResDto> findAll();
}
