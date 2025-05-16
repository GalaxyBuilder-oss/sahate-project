package com.example.demo.dto.product;

import lombok.Data;

@Data
public class ProductReqDto {
    
    private String productName;

    private String description;

    private Integer weight;

    private String coverImage;

    // store id FK
    private Long storeId;

}
