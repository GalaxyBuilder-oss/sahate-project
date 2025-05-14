package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductDetailReqDto {
    private Integer qty;
    private Integer price;
    private Long productId;
    private Long colorId;
    private Long sizeId;
}
