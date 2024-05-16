package com.example.teste.service;

import com.example.teste.dto.request.CreateTransactionRequestDTO;
import com.example.teste.dto.response.TransactionResponseDTO;
import com.example.teste.entity.CustomerEntity;
import com.example.teste.exception.InvalidBalanceValueException;
import com.example.teste.exception.UserNotFoundException;
import com.example.teste.repository.CustomerRepository;
import com.example.teste.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private TransactionServiceImpl service;

    private CreateTransactionRequestDTO requestDTO;
    private Integer id = 1;
    private CustomerEntity customerEntity;

    @Test
    void whenCreateTransaction_withNonExistentClient_shouldThrowException() {
        requestDTO = new CreateTransactionRequestDTO(100, "D");

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            service.createTransaction(String.valueOf(id), requestDTO);
        });
    }

    @Test
    void whenCreateTransacion_withAmountBiggerThanSaldo_shouldThrowException() {
        requestDTO = new CreateTransactionRequestDTO(1500, "D");
        customerEntity = new CustomerEntity("Vandeilson", 1499, 2000);

        when(customerRepository.findById(id)).thenReturn(Optional.of(customerEntity));

        assertThrows(InvalidBalanceValueException.class, () -> {
            service.createTransaction(String.valueOf(id), requestDTO);
        });
    }

    @Test
    void whenCreateTransaction_withValidInput_shouldReturnClienteWithNewSaldo() {
        requestDTO = new CreateTransactionRequestDTO(150, "D");
        customerEntity = new CustomerEntity("Vandeilson", 1500, 2000);

        when(customerRepository.findById(id)).thenReturn(Optional.of(customerEntity));

        var expectedResponse = new TransactionResponseDTO(2000, 1350);
        var actualResponse = service.createTransaction(String.valueOf(id), requestDTO);

        assertEquals(expectedResponse.saldo(), actualResponse.saldo());

    }
}
