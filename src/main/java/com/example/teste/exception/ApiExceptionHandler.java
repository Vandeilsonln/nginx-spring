package com.example.teste.exception;

import com.example.teste.dto.request.CreateTransactionRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

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
    public ResponseEntity<ApiErrorResponse> handleBindExceptions(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return ResponseEntity.unprocessableEntity().body(new ApiErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), MALFORMED_VALUE_TYPE.name(), errors));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        logError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponse(1, ex.getMessage(), Map.of()));
    }

    @ExceptionHandler(InvalidBalanceValueException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidBalanceValueException(InvalidBalanceValueException ex) {
        logError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ApiErrorResponse(2, "not enough funds", Map.of()));
    }

    private String getFieldName(String fieldName, Class clazz) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            JsonProperty jsonPropertyAnnotation = field.getAnnotation(JsonProperty.class);
            if (jsonPropertyAnnotation != null) {
                return jsonPropertyAnnotation.value();
            }
        } catch (NoSuchFieldException | SecurityException e) {
            LOGGER.error(e.getMessage());
        }
        return fieldName;
    }

    private void logError(String message) {
        LOGGER.error(message);
    }
}

