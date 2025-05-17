package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StoreRequestReqDto {
   
    private Long userId; 
    private String storeName;
    private String status;
    private LocalDateTime date;
    
}
