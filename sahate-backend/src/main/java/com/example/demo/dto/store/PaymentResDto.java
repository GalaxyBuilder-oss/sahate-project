package com.example.demo.dto.store;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PaymentResDto {

    private Long id;
    private Long amount;
    private String status;
    private LocalDateTime date;

    private String redirectUrl; // Untuk kirim URL pembayaran ke client

    


    // fk orders //
    private Long orderId;

}
