package com.example.demo.dto;
import lombok.Data;

@Data
public class ShippingResDto {
    private Long id;
    private Long orderId;
    private Long expeditionId;
    private int ongkir;
    private String status;
    private String shippingAdress;
}
