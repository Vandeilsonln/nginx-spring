package com.example.teste.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record ExtratoResponseDTO(
    @JsonProperty("saldo") Saldo saldo,
    @JsonProperty("ultimas_transacoes") List<TransacaoResponseDTO> ultimasTransacoes
) {

    public record Saldo(
        @JsonProperty("total") int total,
        @JsonProperty("data_extrato") LocalDateTime date,
        @JsonProperty("limite") int limite) {
    }

    public record TransacaoResponseDTO(
        @JsonProperty("valor") int valor,
        @JsonProperty("tipo") String tipo,
        @JsonProperty("descricao") String descricao,
        @JsonProperty("realizada_em") LocalDateTime createdAt) {
    }

}
