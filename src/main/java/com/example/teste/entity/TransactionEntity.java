package com.example.teste.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "customer_id")
    private CustomerEntity customerId;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    public TransactionEntity() {
    }

    public Integer getId() {
        return id;
    }

    public CustomerEntity getCustomerId() {
        return customerId;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public TransactionEntity(CustomerEntity customerId, Integer amount, String type, LocalDateTime createdAt) {
        this.customerId = customerId;
        this.amount = amount;
        this.type = type;
        this.createdAt = createdAt;
    }
}
