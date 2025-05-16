package com.example.demo.dto.store;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StoreRequestReqDto {
    private Long id;
    private Long userId; 
    private String storeName;
    private String status;
    private LocalDateTime date;
    
}
