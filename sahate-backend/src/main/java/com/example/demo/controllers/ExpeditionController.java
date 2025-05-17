package com.example.demo.controllers;

import com.example.demo.dto.store.ExpeditionReqDto;
import com.example.demo.services.ExpeditionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/expedition")
@Tag(name = "Expedition")
public class ExpeditionController {

    @Autowired
    private ExpeditionService expeditionService;

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        try {
            return ResponseEntity.ok(expeditionService.findAll());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(expeditionService.findById(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody ExpeditionReqDto dto) {
        try {
            return ResponseEntity.ok(expeditionService.create(dto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestParam Long id, @RequestBody ExpeditionReqDto dto) {
        try {
            return ResponseEntity.ok(expeditionService.update(id, dto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity<Object> delete(@RequestParam Long id) {
        try {
            expeditionService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
