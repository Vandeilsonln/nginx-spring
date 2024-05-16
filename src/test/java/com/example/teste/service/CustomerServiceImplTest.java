package com.example.teste.service;

import com.example.teste.entity.CustomerEntity;
import com.example.teste.entity.TransactionEntity;
import com.example.teste.exception.UserNotFoundException;
import com.example.teste.repository.CustomerRepository;
import com.example.teste.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class CustomerServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl service;

    private String id = "1";
    private TransactionEntity transactionEntity;
    private CustomerEntity customerEntity;
    private LocalDateTime time;
    @Test
    void whenGetTransaction_fromUserThatDoesNotExist_shouldThrowException() {

        when(customerRepository.findById(Integer.valueOf(id))).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            service.getTransactions(id);
        });
    }

    @Test
    void whenGetTransaction_shouldReturnCorrectObject() {
        customerEntity = new CustomerEntity(Integer.valueOf(id), "Vandeilson", 3250, 8000);
        transactionEntity = new TransactionEntity(
                customerEntity, 15, "C", time);

        when(customerRepository.findById(Integer.valueOf(id))).thenReturn(Optional.of(customerEntity));
        when(transactionRepository.findByCustomerId(Integer.valueOf(id))).thenReturn(List.of(transactionEntity));

        var actualResponse = service.getTransactions(String.valueOf(id));

        assertEquals(1, actualResponse.transactions().size());
        assertEquals(15, actualResponse.transactions().get(0).valor());
        assertEquals(8000, actualResponse.balanceDTO().limit());
    }
}
