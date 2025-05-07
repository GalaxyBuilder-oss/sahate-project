package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReqDto {
    
    private String productName;

    private String description;

    private Integer weight;

    private String coverImage;

    // store id FK
    private Long storeId;

}
