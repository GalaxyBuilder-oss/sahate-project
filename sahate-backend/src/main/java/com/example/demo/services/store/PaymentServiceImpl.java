package com.example.demo.services.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.store.PaymentReqDto;
import com.example.demo.dto.store.PaymentResDto;
import com.example.demo.entities.store.Payment;
import com.example.demo.repositories.orders.OrderRepository;
import com.example.demo.repositories.store.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public PaymentResDto create(PaymentReqDto dto) {

        try {
            Payment payment = fromDto(dto);
            return toDto(paymentRepository.save(payment));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PaymentResDto update(Long id, PaymentReqDto dto) {

        try {
            Payment payment = paymentRepository.findById(id).orElse(null);
            if (payment == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment not found");
            }
            payment.setDate(dto.getDate());
            payment.setStatus(dto.getStatus());
            payment.setAmount(dto.getAmount());
            payment.setOrder(orderRepository.findById(dto.getOrderId()).orElse(null));
            return toDto(paymentRepository.save(payment));

        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public void delete(Long id) {

        try {
            paymentRepository.deleteById(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public PaymentResDto findById(Long id) {

        try {
            Payment payment = paymentRepository.findById(id).orElse(null);
            if (payment == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment not found");
            }
            return toDto(payment);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public List<PaymentResDto> findAll() {

        return paymentRepository.findAll().stream().map(this::toDto).toList();
    }

    private Payment fromDto(PaymentReqDto dto) {
        Payment payment = new Payment();
        payment.setDate(dto.getDate());
        payment.setStatus(dto.getStatus());
        payment.setAmount(dto.getAmount());
        payment.setOrder(orderRepository.findById(dto.getOrderId()).orElse(null));
        return payment;
    }

    private PaymentResDto toDto(Payment save) {
        PaymentResDto dto = new PaymentResDto();
        dto.setId(save.getId());
        dto.setAmount(save.getAmount());
        dto.setDate(save.getDate());
        dto.setStatus(save.getStatus());
        dto.setOrderId(save.getOrder().getId());
        return dto;
    }

}
