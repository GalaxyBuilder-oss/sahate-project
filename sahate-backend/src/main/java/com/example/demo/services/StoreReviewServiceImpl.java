package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.store.StoreReviewReqDto;
import com.example.demo.dto.store.StoreReviewResDto;
import com.example.demo.entities.StoreReview;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.repositories.StoreReviewRepository;


@Service
public class StoreReviewServiceImpl implements StoreReviewService{
    
    
    @Autowired
    private StoreReviewRepository storeReviewRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StoreRepository storeRepository;

    @Override
    public StoreReviewResDto create(StoreReviewReqDto dto) {
        try {
            StoreReview storeReview = fromDto(dto);
            return toDto(storeReviewRepository.save(storeReview));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }
    @Override
    public StoreReviewResDto update(Long id, StoreReviewReqDto dto) {
        try {
            StoreReview storeReview = storeReviewRepository.findById(id).orElse(null);
            if (storeReview == null) {
                throw new RuntimeException("Store review not found");
            }
            storeReview.setRating(dto.getRating());
            storeReview.setReview(dto.getReview());
//            storeReview.setCustomer(customerRepository.findById(dto.getCustomerId()).orElse(null));
            storeReview.setDate(dto.getDate());
//            storeReview.setStore(storeRepository.findById(dto.getStoreId()).orElse(null));
            return toDto(storeReviewRepository.save(storeReview));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }
    @Override
    public void delete(Long id) {
        try {
            storeReviewRepository.deleteById(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public StoreReviewResDto findById(Long id) {
        try {
            StoreReview storeReview = storeReviewRepository.findById(id).orElse(null);
            if (storeReview == null) {
                throw new RuntimeException("Store review not found");
            }
            return toDto(storeReview);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }
    @Override
    public List<StoreReviewResDto> findAll() {
        try {
            return storeReviewRepository.findAll().stream()
                    .map(this::toDto)
                    .toList();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    private StoreReview fromDto(StoreReviewReqDto dto) {
        StoreReview storeReview = new StoreReview();
        
        storeReview.setRating(dto.getRating());
        storeReview.setReview(dto.getReview());
//        storeReview.setCustomer(customerRepository.findById(dto.getCustomerId()).orElse(null));
        storeReview.setDate(dto.getDate());
//        storeReview.setStore(storeRepository.findById(dto.getStoreId()).orElse(null));
        return storeReview;
    } 

    private StoreReviewResDto toDto(StoreReview storeReview) {
        StoreReviewResDto dto = new StoreReviewResDto();
        
        dto.setId(storeReview.getId());
        dto.setRating(storeReview.getRating());
        dto.setReview(storeReview.getReview());
        dto.setDate(storeReview.getDate());
//        dto.setStoreId(storeReview.getStore().getId());
//        dto.setCustomerId(storeReview.getCustomer().getId());
        return dto;
    }
}
