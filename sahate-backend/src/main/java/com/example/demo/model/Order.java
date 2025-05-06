package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "delivery_status")
    private String deliveryStatus;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    //buyer id FK (Users)
    // store id FK (Users)
    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private Customer buyer;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
