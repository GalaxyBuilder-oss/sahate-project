package com.example.demo.dto;

import lombok.Data;

@Data
public class UserReqDto {
    private String email;
    private String password;
    private boolean status;
}
