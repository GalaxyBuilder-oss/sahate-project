package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReqDto {

    private Long amount;
    private String status;
    private LocalDateTime date;

    // fk orders //
    private Long orderId;

}
