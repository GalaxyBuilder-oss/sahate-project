package com.example.demo.dto;

import lombok.Data;

@Data
public class StoreReq {
    private String storeName;
    private String storeAddress;
    private String description;
    private String accessStatus;
    private Integer totalProfit;
    private Long userId;
}
