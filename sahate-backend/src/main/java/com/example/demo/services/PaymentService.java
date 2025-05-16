package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.store.PaymentReqDto;
import com.example.demo.dto.store.PaymentResDto;

public interface PaymentService {
    PaymentResDto create(PaymentReqDto dto);

    PaymentResDto update(Long id, PaymentReqDto dto);

    void delete(Long id);

    PaymentResDto findById(Long id);

    List<PaymentResDto> findAll();
}
