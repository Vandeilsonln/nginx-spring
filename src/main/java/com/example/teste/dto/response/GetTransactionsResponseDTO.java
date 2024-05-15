package com.example.teste.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record GetTransactionsResponseDTO(
    @JsonProperty("balance") BalanceDTO balanceDTO,
    @JsonProperty("transactions") List<TransactionDataDTO> transactions
) {

    public record BalanceDTO(
        @JsonProperty("total") int total,
        @JsonProperty("balance_date") LocalDateTime date,
        @JsonProperty("limit") int limit) {
    }

    public record TransactionDataDTO(
        @JsonProperty("value") int valor,
        @JsonProperty("type") String tipo,
        @JsonProperty("created_at") LocalDateTime createdAt) {
    }

}
