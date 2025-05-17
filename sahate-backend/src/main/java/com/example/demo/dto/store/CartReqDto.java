package com.example.demo.dto.store;

import lombok.Data;

@Data
public class CartReqDto {
    private int quantity;
    private Long userId;
    private Long productDetailId;
}
