package com.example.demo.dto;

import com.example.demo.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class CustomerReqDto {
    private String name;
    private String placeBirth;
    private LocalDate timeBirth;
    private String address;
    private String numberPhone;
    private Long user_id;
}
