package com.example.demo.dto.order;

import lombok.Data;

@Data
public class OrderDetailReqDto {
    
    private Integer qty;

    private Integer unitPrice;

    private Long orderId;

    private Long productDetailId;

}
