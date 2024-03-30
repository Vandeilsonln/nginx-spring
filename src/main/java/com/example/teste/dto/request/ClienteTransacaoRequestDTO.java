package com.example.teste.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClienteTransacaoRequestDTO(
    @JsonProperty("valor") Integer valor,
    @JsonProperty("tipo") String tipo,
    @JsonProperty("descricao") String descricao) {

}
