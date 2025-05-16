package com.example.demo.services;

import com.example.demo.dto.product.ProductDetailReqDto;
import com.example.demo.dto.product.ProductDetailResDto;
import com.example.demo.entities.Color;
import com.example.demo.entities.Product;
import com.example.demo.entities.ProductDetail;
import com.example.demo.entities.Size;
import com.example.demo.repositories.ColorRepository;
import com.example.demo.repositories.ProductDetailRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SizeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public ProductDetailResDto create(ProductDetailReqDto dto) {
        ProductDetail detail = fromDto(dto);
        return toDto(productDetailRepository.save(detail));
    }

    @Override
    public ProductDetailResDto update(Long id, ProductDetailReqDto dto) {
        ProductDetail existing = productDetailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product detail not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found"));
        Color color = colorRepository.findById(dto.getColorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Color not found"));
        Size size = sizeRepository.findById(dto.getSizeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size not found"));

        existing.setQty(dto.getQty());
        existing.setPrice(dto.getPrice());
        existing.setProduct(product);
        existing.setColor(color);
        existing.setSize(size);

        return toDto(productDetailRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!productDetailRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product detail not found");
        }
        productDetailRepository.deleteById(id);
    }

    @Override
    public ProductDetailResDto findById(Long id) {
        ProductDetail detail = productDetailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product detail not found"));
        return toDto(detail);
    }

    @Override
    public List<ProductDetailResDto> findAll() {
        return productDetailRepository.findAll().stream().map(this::toDto).toList();
    }

    // Helpers
    private ProductDetail fromDto(ProductDetailReqDto dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found"));
        Color color = colorRepository.findById(dto.getColorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Color not found"));
        Size size = sizeRepository.findById(dto.getSizeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size not found"));

        ProductDetail detail = new ProductDetail();
        detail.setQty(dto.getQty());
        detail.setPrice(dto.getPrice());
        detail.setProduct(product);
        detail.setColor(color);
        detail.setSize(size);
        return detail;
    }

    private ProductDetailResDto toDto(ProductDetail detail) {
        ProductDetailResDto dto = new ProductDetailResDto();
        dto.setId(detail.getId());
        dto.setQty(detail.getQty());
        dto.setPrice(detail.getPrice());
        dto.setProductId(detail.getProduct().getId());
        dto.setColorId(detail.getColor().getId());
        dto.setSizeId(detail.getSize().getId());
        return dto;
    }
}
