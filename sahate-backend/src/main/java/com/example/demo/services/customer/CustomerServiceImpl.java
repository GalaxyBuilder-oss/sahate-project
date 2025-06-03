package com.example.demo.services.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.customer.CustomerReqDto;
import com.example.demo.dto.customer.CustomerResDto;
import com.example.demo.entities.customer.Customer;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.customer.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomerResDto create(CustomerReqDto dto) {
        try {
            Customer customer = fromDto(dto);
            return toDto(customerRepository.save(customer));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    private CustomerResDto toDto(Customer save) {
        CustomerResDto dto = new CustomerResDto();
        dto.setId(save.getId());
        dto.setName(save.getName());
        dto.setPlaceBirth(save.getPlaceBirth());
        dto.setTimeBirth(save.getTimeBirth());
        dto.setAddress(save.getAddress());
        dto.setNumberPhone(save.getNumberPhone());
        dto.setUserId(save.getUser().getId());
        return dto;
    }

    private Customer fromDto(CustomerReqDto dto) throws RuntimeException {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setPlaceBirth(dto.getPlaceBirth());
        customer.setTimeBirth(dto.getTimeBirth());
        customer.setAddress(dto.getAddress());
        customer.setNumberPhone(dto.getNumberPhone());
        customer.setUser(userRepository.findById(dto.getUserId()).orElse(null));
        return customer;
    }

    @Override
    public CustomerResDto update(Long id, CustomerReqDto dto) {
        try {
            Customer customer = customerRepository.findById(id).orElse(null);
            if (customer == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not found");
            }
            customer.setName(dto.getName());
            customer.setPlaceBirth(dto.getPlaceBirth());
            customer.setTimeBirth(dto.getTimeBirth());
            customer.setAddress(dto.getAddress());
            customer.setNumberPhone(dto.getNumberPhone());
            customer.setUser(userRepository.findById(dto.getUserId()).orElse(null));
            return toDto(customerRepository.save(customer));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public CustomerResDto findById(Long id) {
        try {
            Customer customer = customerRepository.findById(id).orElse(null);
            if (customer == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not found");
            }
            return toDto(customer);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong", e);
        }
    }

    @Override
    public List<CustomerResDto> findAll() {
        return customerRepository.findAll().stream().map(this::toDto).toList();
    }
}
