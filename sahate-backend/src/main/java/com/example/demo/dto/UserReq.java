package com.example.demo.dto;

import lombok.Data;

@Data
public class UserReq {
    private String email;
    private String password;
    private boolean status;
}
