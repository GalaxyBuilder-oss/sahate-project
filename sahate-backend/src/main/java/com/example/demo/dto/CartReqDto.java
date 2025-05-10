package com.example.demo.dto;

import com.example.demo.entities.ProductDetail;
import lombok.Data;

@Data
public class CartReqDto {
    private int quantity;
    private Long userId;
    private Long productDetailId;
}
