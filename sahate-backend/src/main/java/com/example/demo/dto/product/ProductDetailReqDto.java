package com.example.demo.dto.product;

import lombok.Data;

@Data
public class ProductDetailReqDto {
    private Integer qty;
    private Integer price;
    private Long productId;
    private Long colorId;
    private Long sizeId;
}
