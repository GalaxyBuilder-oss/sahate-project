package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.store.StoreRequestReqDto;
import com.example.demo.dto.store.StoreRequestResDto;
import com.example.demo.services.StoreRequestServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/store-requests")
public class StoreRequestController {

    @Autowired
    private StoreRequestServiceImpl storeRequestService;

    // ✅ 1. Toko mengajukan permintaan pembukaan toko
    @PostMapping
    public ResponseEntity<StoreRequestResDto> create(@RequestBody @Valid StoreRequestReqDto dto) {
        return ResponseEntity.ok(storeRequestService.create(dto));
    }

    // ✅ 2. Admin menyetujui atau menolak permintaan
    @PutMapping("/{id}/status")
    public ResponseEntity<StoreRequestResDto> updateStatus(
            @PathVariable Long id,
            @RequestParam("status") String status // example: APPROVED or REJECTED
    ) {
        return ResponseEntity.ok(storeRequestService.updateStatus(id, status));
    }

    // ✅ 3. Admin melihat semua permintaan
    @GetMapping
    public ResponseEntity<List<StoreRequestResDto>> findAll() {
        return ResponseEntity.ok(storeRequestService.findAll());
    }

    // ✅ 4. Melihat detail permintaan tertentu
    @GetMapping("/{id}")
    public ResponseEntity<StoreRequestResDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(storeRequestService.findById(id));
    }

    // ✅ 5. Hapus request (opsional)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        storeRequestService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

