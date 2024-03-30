package com.example.teste.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record ApiErrorResponse(

    @JsonProperty("code")
    Integer code,

    @JsonProperty("type")
    String reason,

    @JsonProperty("errors")
    Map<String, String> errors) {

}
