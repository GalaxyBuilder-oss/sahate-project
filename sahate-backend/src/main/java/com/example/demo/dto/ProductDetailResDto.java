package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductDetailResDto {
    private Long id;
    private Integer qty;
    private Integer unitPrice;
    private Long productId;
    private Long colorId;
    private Long sizeId;
}
