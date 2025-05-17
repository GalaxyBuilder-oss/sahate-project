package com.example.demo.dto.store;

import lombok.Data;

@Data
public class CartResDto {
    private long id;
    private int quantity;
    private Long userId;
    private Long productDetailId;
}
