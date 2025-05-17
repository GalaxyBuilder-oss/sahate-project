package com.example.demo.dto.store;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SalesReportResDto {
    private Long id;
    private Long storeId;    
    private LocalDateTime date;
    private Integer totalSales;
    private Integer totalProfit;
}
