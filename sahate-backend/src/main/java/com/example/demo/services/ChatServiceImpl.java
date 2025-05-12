package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.ChatReqDto;
import com.example.demo.dto.ChatResDto;
import com.example.demo.entities.Chat;
import com.example.demo.entities.User;
import com.example.demo.repositories.ChatRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ChatResDto create(ChatReqDto dto) {
        try {
            Chat chat = fromDto(dto);
            return toDto(chatRepository.save(chat));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"There is something wrong",e);
        }
    }

    private ChatResDto toDto(Chat chat) {
        ChatResDto dto = new ChatResDto();
        dto.setId(chat.getId());
        dto.setMessage(chat.getMessage());
        dto.setSendDateTime(chat.getSendDateTime());
        dto.setSenderId(chat.getSender().getId());
        dto.setReceiverId(chat.getReceiver().getId());
        return dto;
    }

    private Chat fromDto(ChatReqDto dto) {
        User sender = userRepository.findById(dto.getSenderId()).orElse(null);
        User receiver = userRepository.findById(dto.getReceiverId()).orElse(null);
        Chat chat = new Chat();
        chat.setSender(sender);
        chat.setReceiver(receiver);
        chat.setMessage(dto.getMessage());
        chat.setSendDateTime(LocalDateTime.now());
        return chat;
    }

    @Override
    public ChatResDto update(Long id, ChatReqDto dto) {
        try {
            Chat chat = chatRepository.findById(id).orElse(null);
            if (chat == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chat not found");
            }
            chat.setMessage(dto.getMessage());
            return toDto(chatRepository.save(chat));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            chatRepository.deleteById(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public ChatResDto findById(Long id) {
        try {
            Chat chat = chatRepository.findById(id).orElse(null);
            if (chat == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chat not found");
            }
            return toDto(chat);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public List<ChatResDto> findAll() {
        return chatRepository.findAll().stream().map(this::toDto).toList();
    }
}
