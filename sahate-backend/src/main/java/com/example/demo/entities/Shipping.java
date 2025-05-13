package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "orders_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "expedition_id", nullable = false)
    private Expedition expedition;

    private Integer postage;

    @Column(name = "shipping_status")
    private String status;

    @Column(name = "shipping_address")
    private String shippingAddress;
}
