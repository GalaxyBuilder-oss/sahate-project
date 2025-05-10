package com.example.demo.dto;

import lombok.Data;

@Data
public class OrderDetailResDto {
    private Long id;
    
    private Integer qty;

    private Integer unitPrice;

    private Long orderId;

    private Long productDetailId;

}
