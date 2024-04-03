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
@Table(name = "transacoes")
public class TransacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "cliente_id")
    private ClienteEntity clienteId;

    @Column(nullable = false)
    private Integer valor;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false, name = "realizada_em")
    private LocalDateTime realizadaEm;

    public TransacaoEntity() {
    }

    public Integer getId() {
        return id;
    }

    public ClienteEntity getClienteId() {
        return clienteId;
    }

    public Integer getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getRealizadaEm() {
        return realizadaEm;
    }

    public TransacaoEntity(final ClienteEntity clienteId, final Integer valor, final String tipo, final LocalDateTime realizadaEm) {
        this.clienteId = clienteId;
        this.valor = valor;
        this.tipo = tipo;
        this.realizadaEm = realizadaEm;
    }

}
