package com.example.demo.services.store;

import java.util.List;

import com.example.demo.dto.store.PaymentReqDto;
import com.example.demo.dto.store.PaymentResDto;

public interface PaymentService {
    void handleMidtransNotification(Map<String, Object> notification);
}
