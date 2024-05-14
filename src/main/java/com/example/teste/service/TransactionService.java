package com.example.teste.service;

import com.example.teste.dto.request.CreateTransactionRequestDTO;
import com.example.teste.dto.response.TransactionResponseDTO;

public interface TransactionService {

    TransactionResponseDTO createTransaction(final String id, final CreateTransactionRequestDTO requestDTO);

}
