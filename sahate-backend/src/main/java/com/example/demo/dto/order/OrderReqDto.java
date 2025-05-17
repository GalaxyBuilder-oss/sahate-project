package com.example.demo.dto.order;

import java.time.LocalDateTime;

import lombok.Data;

@Data
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
