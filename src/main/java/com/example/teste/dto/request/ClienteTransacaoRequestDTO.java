package com.example.teste.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ClienteTransacaoRequestDTO(
    @JsonProperty("valor")
    @NotNull(message = "atributo não pode ser nulo")
    @Positive(message = "atributo precisa ser positivo")
    Integer amount,

    @JsonProperty("tipo")
    @NotNull(message = "O tipo não pode ser nulo")
    @Pattern(regexp = "[cd]", message = "O tipo deve ser 'c' para crédito ou 'd' para débito")
    String tipo,

    @JsonProperty("descricao")
    @NotNull(message = "A descrição não pode ser nula")
    @Size(min = 1, max = 10, message = "A descrição deve ter entre 1 e 10 caracteres")
    String descricao
) {

}
