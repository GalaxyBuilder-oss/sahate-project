package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResDto {
    private Long id;
    
    private String productName;

    private String description;

    private Integer weight;

    private String coverImage;

    // store id FK
    private Long storeId;

}
