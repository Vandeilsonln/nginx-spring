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
    @Pattern(regexp = "[cdCD]", message = "type must be either D (debit) or C (credit)")
    String type,

    @JsonProperty("description")
    @NotNull(message = "description can not be null")
    @Size(min = 1, max = 10, message = "description length must be between 1 and 10 characters")
    String description
) {

}
