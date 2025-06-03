package com.example.demo.services.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.product.ProductReqDto;
import com.example.demo.dto.product.ProductResDto;
import com.example.demo.entities.product.Product;
import com.example.demo.repositories.product.ProductRepository;
import com.example.demo.repositories.store.StoreRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResDto create(ProductReqDto dto) {

        try {
            Product product = fromDto(dto);
            return toDto(productRepository.save(product));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductResDto update(Long id, ProductReqDto dto) {

        try {
            Product product = productRepository.findById(id).orElse(null);
            if (product == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
            }
            product.setCoverImage(dto.getCoverImage());
            product.setDescription(dto.getDescription());
            product.setProductName(dto.getProductName());
            product.setWeight(dto.getWeight());
            product.setStore(storeRepository.findById(dto.getStoreId()).orElse(null));
            return toDto(productRepository.save(product));

        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public void delete(Long id) {

        try {
            productRepository.deleteById(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public ProductResDto findById(Long id) {

        try {
            Product product = productRepository.findById(id).orElse(null);
            if (product == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
            }
            return toDto(product);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public List<ProductResDto> findAll() {

        return productRepository.findAll().stream().map(this::toDto).toList();
    }

    private Product fromDto(ProductReqDto dto) {
        Product product = new Product();
        product.setCoverImage(dto.getCoverImage());
        product.setDescription(dto.getDescription());
        product.setProductName(dto.getProductName());
        product.setWeight(dto.getWeight());
        product.setStore(storeRepository.findById(dto.getStoreId()).orElse(null));
        return product;

    }

    private ProductResDto toDto(Product save) {
        ProductResDto dto = new ProductResDto();
        dto.setId(save.getId());
        dto.setCoverImage(save.getCoverImage());
        dto.setDescription(save.getDescription());
        dto.setProductName(save.getProductName());
        dto.setWeight(save.getWeight());
        dto.setStoreId(save.getStore().getId());
        return dto;
    }

}
