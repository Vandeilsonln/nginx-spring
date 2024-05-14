package com.example.teste.service;

import com.example.teste.dto.request.CreateTransactionRequestDTO;
import com.example.teste.dto.response.TransacaoResponseDTO;
import com.example.teste.entity.ClienteEntity;
import com.example.teste.exception.LimiteEstouradoException;
import com.example.teste.exception.UserNotFoundException;
import com.example.teste.repository.ClienteRepository;
import com.example.teste.repository.TransacaoRepository;
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
    private TransacaoRepository transacaoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private TransactionServiceImpl service;

    private CreateTransactionRequestDTO requestDTO;
    private Integer id = 1;
    private ClienteEntity clienteEntity;

    @Test
    void whenCreateTransaction_withNonExistentClient_shouldThrowException() {
        requestDTO = new CreateTransactionRequestDTO(100, "D", "Default");

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            service.createTransaction(String.valueOf(id), requestDTO);
        });
    }

    @Test
    void whenCreateTransacion_withAmountBiggerThanSaldo_shouldThrowException() {
        requestDTO = new CreateTransactionRequestDTO(1500, "D", "Default");
        clienteEntity = new ClienteEntity("Vandeilson", 1499, 2000);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteEntity));

        assertThrows(LimiteEstouradoException.class, () -> {
            service.createTransaction(String.valueOf(id), requestDTO);
        });
    }

    @Test
    void whenCreateTransaction_withValidInput_shouldReturnClienteWithNewSaldo() {
        requestDTO = new CreateTransactionRequestDTO(150, "D", "Default");
        clienteEntity = new ClienteEntity("Vandeilson", 1500, 2000);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteEntity));

        var expectedResponse = new TransacaoResponseDTO(2000, 1350);
        var actualResponse = service.createTransaction(String.valueOf(id), requestDTO);

        assertEquals(expectedResponse.saldo(), actualResponse.saldo());

    }
}
