package com.example.teste.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionResponseDTO(
    @JsonProperty("limit") Integer limite,
    @JsonProperty("balance") Integer saldo
) {

}
