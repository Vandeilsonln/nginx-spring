package com.example.teste.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClienteTransacaoResponseDTO(
    @JsonProperty("limite") Integer limite,
    @JsonProperty("saldo") Integer saldo
) {

}
