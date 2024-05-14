package com.example.teste.controller;

import com.example.teste.dto.request.CreateTransactionRequestDTO;
import com.example.teste.dto.response.TransacaoResponseDTO;
import com.example.teste.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransacaoController {

    @Autowired
    private TransactionService transactionServiceImpl;

    @PostMapping("{id}")
    public ResponseEntity<TransacaoResponseDTO> createTransaction(
        @PathVariable final String id, @Valid @RequestBody CreateTransactionRequestDTO requestDTO) {

        var response = transactionServiceImpl.createTransaction(id, requestDTO);

        return ResponseEntity.ok(response);
    }

}
