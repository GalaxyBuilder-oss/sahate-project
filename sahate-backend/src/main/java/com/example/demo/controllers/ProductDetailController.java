package com.example.demo.controllers;

import com.example.demo.dto.product.ProductDetailReqDto;
import com.example.demo.services.ProductDetailService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-detail")
@Tag(name = "Product Detail")
@Slf4j
public class ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody ProductDetailReqDto dto) {
        try {
            return ResponseEntity.ok(productDetailService.create(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ProductDetailReqDto dto) {
    try {
        return ResponseEntity.ok(productDetailService.update(id, dto));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            productDetailService.delete(id);
            return ResponseEntity.ok().body("Product detail deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
    try {
        return ResponseEntity.ok(productDetailService.findById(id));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            return ResponseEntity.ok(productDetailService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
