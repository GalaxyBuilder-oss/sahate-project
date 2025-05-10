package com.example.demo.dto;

import lombok.Data;


@Data
public class ChatReqDto {

    private String message;
    private Long senderId;
    private Long receiverId;

}
