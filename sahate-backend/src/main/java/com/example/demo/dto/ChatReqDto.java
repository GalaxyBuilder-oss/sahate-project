package com.example.demo.dto;

import com.example.demo.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ChatReqDto {

    private String message;
    private LocalDateTime sendDateTime;
    private Long sender_id;
    private Long receiver_id;

}
