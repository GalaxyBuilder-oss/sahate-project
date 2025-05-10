package com.example.demo.services;

import com.example.demo.dto.CartReqDto;
import com.example.demo.dto.CartResDto;
import com.example.demo.dto.CustomerReqDto;
import com.example.demo.dto.CustomerResDto;

import java.util.List;

public interface CustomerService {
    CustomerResDto create(CustomerReqDto dto);
    CustomerResDto update(Long id, CustomerReqDto dto);
    void delete(Long id);
    CustomerResDto findById(Long id);
    List<CustomerResDto> findAll();
}
