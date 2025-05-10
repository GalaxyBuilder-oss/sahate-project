package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReqDto {

    private String deliveryStatus;

    private Integer totalPrice;

    private String paymentMethod;

    private LocalDateTime purchaseDate;

    //buyer id FK (Users)
    // store id FK (Users)
    private Long buyerId;

    private Long storeId;

    
}
