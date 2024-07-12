package com.example.teste.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateTransactionRequestDTO(
    @JsonProperty("amount")
    @NotNull(message = "value can not be null")
    @Positive(message = "value must be greater than 0")
    Integer amount,

    @JsonProperty("type")
    @NotNull(message = "type can not be null")
    @Pattern(regexp = "[CD]", message = "type must be either D (debit) or C (credit)")
    String type

) {

}
