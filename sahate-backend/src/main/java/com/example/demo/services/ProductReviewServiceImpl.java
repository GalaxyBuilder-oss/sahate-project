package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.product.ProductReviewReqDto;
import com.example.demo.dto.product.ProductReviewResDto;
import com.example.demo.entities.customer.Customer;
import com.example.demo.entities.product.Product;
import com.example.demo.entities.product.ProductReview;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ProductReviewRepository;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ProductReviewResDto create(ProductReviewReqDto dto) {
        ProductReview review = fromDto(dto);
        review.setDate(LocalDateTime.now());
        return toDto(productReviewRepository.save(review));
    }

    @Override
    public ProductReviewResDto update(Long id, ProductReviewReqDto dto) {
        ProductReview existing = productReviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found"));
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not found"));

        existing.setRating(dto.getRating());
        existing.setReview(dto.getReview());
       existing.setProduct(product);
       existing.setCustomer(customer);

        return toDto(productReviewRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!productReviewRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        productReviewRepository.deleteById(id);
    }

    @Override
    public ProductReviewResDto findById(Long id) {
        ProductReview review = productReviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));
        return toDto(review);
    }

    @Override
    public List<ProductReviewResDto> findAll() {
        return productReviewRepository.findAll().stream().map(this::toDto).toList();
    }

    private ProductReview fromDto(ProductReviewReqDto dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found"));
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not found"));

        ProductReview review = new ProductReview();
        review.setRating(dto.getRating());
        review.setReview(dto.getReview());
       review.setProduct(product);
       review.setCustomer(customer);
        return review;
    }

    private ProductReviewResDto toDto(ProductReview review) {
        ProductReviewResDto dto = new ProductReviewResDto();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setReview(review.getReview());
        dto.setDate(review.getDate());
       dto.setProductId(review.getProduct().getId());
       dto.setCustomerId(review.getCustomer().getId());
        return dto;
    }
}
