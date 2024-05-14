package com.example.teste.service;

import com.example.teste.dto.request.CreateTransactionRequestDTO;
import com.example.teste.dto.response.TransacaoResponseDTO;

public interface TransactionService {

    TransacaoResponseDTO createTransaction(final String id, final CreateTransactionRequestDTO requestDTO);

}
