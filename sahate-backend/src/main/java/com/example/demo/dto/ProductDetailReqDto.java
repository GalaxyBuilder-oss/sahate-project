package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailReqDto {
    private Integer qty;
    private Integer unitPrice;
    private Long productId;
    private Long colorId;
    private Long sizeId;
}
