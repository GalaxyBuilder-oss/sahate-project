package com.example.demo.entities.orders;

import java.time.LocalDateTime;

import com.example.demo.entities.customer.Customer;
import com.example.demo.entities.store.Store;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shopping_order")
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

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING; // PENDING, SUCCESS, FAILED

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.PENDING; // PENDING, PROCESSING, SHIPPED, COMPLETED

    private String midtransTransactionToken; // Untuk menyimpan token/snap URL dari Midtrans


    //buyer id FK (Users)
    // store id FK (Users)
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer buyer;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
