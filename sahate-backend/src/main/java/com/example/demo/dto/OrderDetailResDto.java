package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResDto {
    private Long id;
    
    private Integer qty;

    private Integer unitPrice;

    private Long orderId;

    private Long productDetailId;

}
