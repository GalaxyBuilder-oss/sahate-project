package com.example.demo.controllers.store;



import java.util.List;

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

import com.example.demo.dto.store.StoreReviewReqDto;
import com.example.demo.dto.store.StoreReviewResDto;
import com.example.demo.services.store.StoreReviewService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Store Review")
@RequestMapping("/api/store-reviews")
public class StoreReviewController {

    @Autowired
    private StoreReviewService storeReviewService;

    @PostMapping
    public ResponseEntity<StoreReviewResDto> create(@RequestBody StoreReviewReqDto dto) {
        StoreReviewResDto result = storeReviewService.create(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreReviewResDto> update(@PathVariable Long id, @RequestBody StoreReviewReqDto dto) {
        StoreReviewResDto result = storeReviewService.update(id, dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        storeReviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreReviewResDto> findById(@PathVariable Long id) {
        StoreReviewResDto result = storeReviewService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<StoreReviewResDto>> findAll() {
        List<StoreReviewResDto> result = storeReviewService.findAll();
        return ResponseEntity.ok(result);
    }

    
}

