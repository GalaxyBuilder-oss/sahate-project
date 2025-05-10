package com.example.demo.dto;

import lombok.Data;

@Data
public class CartReqDto {
    private int quantity;
    private Long userId;
    private Long productDetailId;
}
