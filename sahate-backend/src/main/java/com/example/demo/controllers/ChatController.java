package com.example.demo.controllers;

import com.example.demo.dto.ChatReqDto;
import com.example.demo.services.ChatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
@RequestMapping("/api/chat")
@Tag(name = "Chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        try {
            return ResponseEntity.ok(chatService.findAll());
        } catch (ResponseStatusException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(chatService.findById(id));
        } catch (ResponseStatusException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody ChatReqDto dto) {
        try {
            return ResponseEntity.ok(chatService.create(dto));
        } catch (ResponseStatusException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ChatReqDto dto) {
        try {
            return ResponseEntity.ok(chatService.update(id, dto));
        } catch (ResponseStatusException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            chatService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
