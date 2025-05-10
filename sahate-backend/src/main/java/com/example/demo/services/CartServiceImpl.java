package com.example.demo.services;

import com.example.demo.dto.CartReqDto;
import com.example.demo.dto.CartResDto;
import com.example.demo.entities.Cart;
import com.example.demo.entities.ProductDetail;
import com.example.demo.entities.User;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.ProductDetailRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public CartResDto create(CartReqDto dto) {
        try {
            Cart cart = fromDto(dto);
            return toDto(cartRepository.save(cart));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Ada Yang salah", e);
        }
    }

    private CartResDto toDto(Cart cart) {
        CartResDto dto = new CartResDto();
        dto.setId(cart.getId());
        dto.setQuantity(cart.getQuantity());
        dto.setUserId(cart.getUser().getId());
        dto.setProductDetailId(cart.getProductDetail().getId());
        return dto;
    }

    private Cart fromDto(CartReqDto dto) {
        Cart cart = new Cart();
        User user = dto.getUserId() == null ? null : userRepository.findById(dto.getUserId()).orElse(null);
        ProductDetail productDetail = dto.getProductDetailId() == null ? null : productDetailRepository.findById(dto.getProductDetailId()).orElse(null);
        if (dto.getUserId() != null && !userRepository.existsById(dto.getUserId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found");
        }
        if (dto.getProductDetailId() != null && !productDetailRepository.existsById(dto.getProductDetailId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product Detail not found");
        }
        cart.setUser(user);
        cart.setQuantity(dto.getQuantity());
        cart.setProductDetail(productDetail);
        return cart;
    }

    @Override
    public CartResDto update(Long id, CartReqDto dto) {
        Cart cart = fromDto(dto);
        cart.setId(id);
        return toDto(cartRepository.save(cart));
    }

    @Override
    public void delete(Long id) {
        try {
            cartRepository.deleteById(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Ada Yang salah", e);
        }
    }

    @Override
    public CartResDto findById(Long id) {
        try {
            Cart cart = cartRepository.findById(id).orElse(null);
            if (cart == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cart not found");
            }
            return toDto(cart);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Ada Yang salah", e);
        }
    }

    @Override
    public List<CartResDto> findAll() {
        return cartRepository.findAll().stream().map(this::toDto).toList();
    }
}
