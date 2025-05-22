package com.example.demo.services.customer;

import java.util.List;

import com.example.demo.dto.customer.CustomerReqDto;
import com.example.demo.dto.customer.CustomerResDto;

public interface CustomerService {
    CustomerResDto create(CustomerReqDto dto);
    CustomerResDto update(Long id, CustomerReqDto dto);
    void delete(Long id);
    CustomerResDto findById(Long id);
    List<CustomerResDto> findAll();
}
