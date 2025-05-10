package com.example.demo.services;

import com.example.demo.dto.CartReqDto;
import com.example.demo.dto.CartResDto;
import com.example.demo.dto.ChatReqDto;
import com.example.demo.dto.ChatResDto;

import java.util.List;

public interface ChatService {
    ChatResDto create(ChatReqDto dto);
    ChatResDto update(Long id, ChatReqDto dto);
    void delete(Long id);
    ChatResDto findById(Long id);
    List<ChatResDto> findAll();
}
