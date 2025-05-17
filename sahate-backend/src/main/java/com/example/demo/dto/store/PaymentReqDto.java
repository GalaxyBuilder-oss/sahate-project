package com.example.demo.dto.store;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PaymentReqDto {

    private Long amount;
    private String status;
    private LocalDateTime date;

    // fk orders //
    private Long orderId;

}
