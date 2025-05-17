package com.example.demo.controllers;

import com.example.demo.dto.ChatReqDto;
import com.example.demo.services.ChatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(chatService.findById(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody ChatReqDto dto) {
        try {
            return ResponseEntity.ok(chatService.create(dto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestParam Long id, @RequestBody ChatReqDto dto) {
        try {
            return ResponseEntity.ok(chatService.update(id, dto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity<Object> delete(@RequestParam Long id) {
        try {
            chatService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
