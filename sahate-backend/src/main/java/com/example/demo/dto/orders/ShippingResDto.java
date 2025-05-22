package com.example.demo.dto.orders;
import lombok.Data;

@Data
public class ShippingResDto {
    private Long id;
    private Long orderId;
    private Long expeditionId;
    private int postage;
    private String status;
    private String shippingAddress;
}
