package com.example.teste.service;

import com.example.teste.dto.request.CreateTransactionRequestDTO;
import com.example.teste.dto.response.TransactionResponseDTO;
import com.example.teste.entity.CustomerEntity;
import com.example.teste.entity.TransactionEntity;
import com.example.teste.exception.InvalidBalanceValueException;
import com.example.teste.exception.UserNotFoundException;
import com.example.teste.repository.CustomerRepository;
import com.example.teste.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public TransactionResponseDTO createTransaction(final String id, final CreateTransactionRequestDTO requestDTO) {
        var customer = getCustomer(id);

        if (isDebit(requestDTO)) {
            var newBalance = processDebit(requestDTO, customer);
            customer.setBalance(newBalance);
            customerRepository.updateBalance(newBalance, Integer.valueOf(id));
        }
        transactionRepository.save(new TransactionEntity(customer, requestDTO.amount(), requestDTO.type(), LocalDateTime.now()));
        return new TransactionResponseDTO(customer.getLimits(), customer.getBalance());
    }

    private CustomerEntity getCustomer(String id) {
        return customerRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new UserNotFoundException(id));
    }

    private boolean isDebit(CreateTransactionRequestDTO requestDTO) {
        return "D".equals(requestDTO.type().toUpperCase(Locale.ROOT));
    }

    private int processDebit(CreateTransactionRequestDTO requestDTO, CustomerEntity customer) {
        var updatedBalance = calculateNewBalance(requestDTO, customer);
        if (isNewBalanceLessThanZero(updatedBalance)) {
            throw new InvalidBalanceValueException();
        }
        return updatedBalance;
    }

    private int calculateNewBalance(CreateTransactionRequestDTO requestDTO, CustomerEntity customer) {
        return customer.getBalance() - requestDTO.amount();
    }

    private boolean isNewBalanceLessThanZero(int newBalance) {
        return newBalance < 0;
    }

}
