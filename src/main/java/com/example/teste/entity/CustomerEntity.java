package com.example.teste.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer balance;

    @Column(nullable = false)
    private Integer limits;

    public CustomerEntity(final String name, final Integer balance, final Integer limits) {
        this.name = name;
        this.balance = balance;
        this.limits = limits;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public Integer getLimits() {
        return limits;
    }

    public CustomerEntity() {
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
