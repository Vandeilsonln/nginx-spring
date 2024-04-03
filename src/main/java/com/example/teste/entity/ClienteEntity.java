package com.example.teste.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer saldo;

    @Column(nullable = false)
    private Integer limite;

    public ClienteEntity() {
    }

    public ClienteEntity(final String nome, final Integer saldo, final Integer limite) {
        this.nome = nome;
        this.saldo = saldo;
        this.limite = limite;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public Integer getLimite() {
        return limite;
    }

}
