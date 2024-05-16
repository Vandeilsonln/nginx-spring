package com.example.teste.service;

import com.example.teste.dto.response.GetTransactionsResponseDTO;
import com.example.teste.entity.CustomerEntity;
import com.example.teste.entity.TransactionEntity;
import com.example.teste.exception.UserNotFoundException;
import com.example.teste.repository.CustomerRepository;
import com.example.teste.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public GetTransactionsResponseDTO getTransactions(String id) {
        var customer = getCustomer(id);
        var transactions = transactionRepository.findByCustomerId(customer.getId())
                .stream()
                .map(this::toTransactionDataDTO)
                .collect(Collectors.toList());

        return new GetTransactionsResponseDTO(
                new GetTransactionsResponseDTO.BalanceDTO(
                        customer.getBalance(), LocalDateTime.now(), customer.getLimits()),
                transactions);
    }

    private CustomerEntity getCustomer(String id) {
        return customerRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new UserNotFoundException(id));
    }

    private GetTransactionsResponseDTO.TransactionDataDTO toTransactionDataDTO(TransactionEntity entity) {
        return new GetTransactionsResponseDTO.TransactionDataDTO(entity.getAmount(), entity.getType(), entity.getCreatedAt());
    }
}
