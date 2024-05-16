package com.example.teste.service;

import com.example.teste.dto.response.GetTransactionsResponseDTO;

public interface CustomerService {

    GetTransactionsResponseDTO getTransactions(String id);
}
