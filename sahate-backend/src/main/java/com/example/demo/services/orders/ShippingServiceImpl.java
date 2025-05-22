package com.example.demo.services.orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.orders.ShippingReqDto;
import com.example.demo.dto.orders.ShippingResDto;
import com.example.demo.entities.orders.Order;
import com.example.demo.entities.orders.Shipping;
import com.example.demo.entities.store.Expedition;
import com.example.demo.repositories.orders.OrderRepository;
import com.example.demo.repositories.orders.ShippingRepository;
import com.example.demo.repositories.store.ExpeditionRepository;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ExpeditionRepository expeditionRepository;

    @Override
    public ShippingResDto create(ShippingReqDto dto) {
        Shipping shipping = fromDto(dto);
        return toDto(shippingRepository.save(shipping));
    }

    @Override
    public ShippingResDto update(Long id, ShippingReqDto dto) {
        Shipping existing = shippingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shipping not found"));

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found"));

        Expedition expedition = expeditionRepository.findById(dto.getExpeditionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Expedition not found"));

        existing.setOrder(order);
        existing.setExpedition(expedition);
        existing.setPostage(dto.getPostage());
        existing.setStatus(dto.getStatus());
        existing.setShippingAddress(dto.getShippingAddress());

        return toDto(shippingRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!shippingRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shipping not found");
        }
        shippingRepository.deleteById(id);
    }

    @Override
    public ShippingResDto findById(Long id) {
        Shipping shipping = shippingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shipping not found"));
        return toDto(shipping);
    }

    @Override
    public List<ShippingResDto> findAll() {
        return shippingRepository.findAll().stream().map(this::toDto).toList();
    }

    private Shipping fromDto(ShippingReqDto dto) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found"));

        Expedition expedition = expeditionRepository.findById(dto.getExpeditionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Expedition not found"));

        Shipping shipping = new Shipping();
        shipping.setOrder(order);
        shipping.setExpedition(expedition);
        shipping.setPostage(dto.getPostage());
        shipping.setStatus(dto.getStatus());
        shipping.setShippingAddress(dto.getShippingAddress());

        return shipping;
    }

    private ShippingResDto toDto(Shipping shipping) {
        ShippingResDto dto = new ShippingResDto();
        dto.setId(shipping.getId());
        dto.setOrderId(shipping.getOrder().getId());
        dto.setExpeditionId(shipping.getExpedition().getId());
        dto.setPostage(shipping.getPostage());
        dto.setStatus(shipping.getStatus());
        dto.setShippingAddress(shipping.getShippingAddress());
        return dto;
    }
}
