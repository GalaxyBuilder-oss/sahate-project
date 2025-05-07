package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerResDto {
    private Long id;
    private String name;
    private String placeBirth;
    private LocalDate timeBirth;
    private String address;
    private String numberPhone;
    private Long user_id;
}
