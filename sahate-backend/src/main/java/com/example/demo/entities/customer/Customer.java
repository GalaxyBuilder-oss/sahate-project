package com.example.demo.entities.customer;

import java.time.LocalDate;

import com.example.demo.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "place_birth")
    private String placeBirth;

    @Column(name = "time_birth")
    private LocalDate timeBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "number_phone")
    private String numberPhone;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
