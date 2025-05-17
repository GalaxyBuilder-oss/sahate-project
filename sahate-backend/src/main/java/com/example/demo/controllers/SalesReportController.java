package com.example.demo.controllers;

import com.example.demo.dto.store.SalesReportReqDto;
import com.example.demo.services.SalesReportService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales-report")
@Tag(name = "Sales Report")
@Slf4j
public class SalesReportController {

    @Autowired
    private SalesReportService salesReportService;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody SalesReportReqDto dto) {
        try {
            return ResponseEntity.ok(salesReportService.create(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody SalesReportReqDto dto) {
        try {
            return ResponseEntity.ok(salesReportService.update(id, dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            salesReportService.delete(id);
            return ResponseEntity.ok("Sales report deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(salesReportService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            return ResponseEntity.ok(salesReportService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
