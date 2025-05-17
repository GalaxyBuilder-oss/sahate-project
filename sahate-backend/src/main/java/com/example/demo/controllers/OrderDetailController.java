package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.order.OrderDetailReqDto;
import com.example.demo.services.OrderDetailService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/orderDetail")
@Tag(name = "Order Detail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody OrderDetailReqDto dto) {
        try {
            return ResponseEntity.ok(orderDetailService.create(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody OrderDetailReqDto dto) {
        try {
            return ResponseEntity.ok(orderDetailService.update(id, dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            orderDetailService.delete(id);
            return ResponseEntity.ok().body("Order Detail deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderDetailService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            return ResponseEntity.ok(orderDetailService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
