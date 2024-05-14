package com.example.teste.exception;

import com.example.teste.dto.request.CreateTransactionRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.example.teste.exception.ApiErrorType.MALFORMED_VALUE_TYPE;
import static com.example.teste.exception.ApiErrorType.PAYLOAD_INVALID;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = getFieldName(error.getField(), CreateTransactionRequestDTO.class);
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.unprocessableEntity().body(new ApiErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), PAYLOAD_INVALID.name(), errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleBindExceptions(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return ResponseEntity.unprocessableEntity().body(new ApiErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), MALFORMED_VALUE_TYPE.name(), errors));
    }

    private String getFieldName(String fieldName, Class clazz) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            JsonProperty jsonPropertyAnnotation = field.getAnnotation(JsonProperty.class);
            if (jsonPropertyAnnotation != null) {
                return jsonPropertyAnnotation.value();
            }
        } catch (NoSuchFieldException | SecurityException e) {
            // Log the exception or handle it as needed
        }
        return fieldName;
    }
}

