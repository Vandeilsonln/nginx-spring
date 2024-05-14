package com.example.teste.service;

import com.example.teste.dto.response.GetTransactionsResponseDTO;

public interface ConsumerService {

    GetTransactionsResponseDTO getTransactions(String id);
}
