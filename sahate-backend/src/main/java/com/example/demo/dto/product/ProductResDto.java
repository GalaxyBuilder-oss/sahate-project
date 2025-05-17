package com.example.demo.dto.product;

import lombok.Data;

@Data
public class ProductResDto {
    private Long id;
    
    private String productName;

    private String description;

    private Integer weight;

    private String coverImage;

    // store id FK
    private Long storeId;

}
