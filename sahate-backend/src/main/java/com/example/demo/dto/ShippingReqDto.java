package com.example.demo.dto;
import lombok.Data;

@Data
public class ShippingReqDto {
    private Long orderId;
    private Long expeditionId;
    private int postage;
    private String status;
    private String shippingAddress;
}
