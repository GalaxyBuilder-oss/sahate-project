package com.example.demo.entities.store;

import java.time.LocalDateTime;

import com.example.demo.entities.orders.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private String status;
    private LocalDateTime date;

    // fk orders //
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
