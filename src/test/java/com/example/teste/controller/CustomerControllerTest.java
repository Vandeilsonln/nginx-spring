package com.example.teste.controller;

import com.example.teste.dto.response.GetTransactionsResponseDTO;
import com.example.teste.exception.ApiErrorResponse;
import com.example.teste.exception.ApiExceptionHandler;
import com.example.teste.exception.UserNotFoundException;
import com.example.teste.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest({CustomerController.class, ApiErrorResponse.class, ApiExceptionHandler.class})
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerServiceImpl service;

    @InjectMocks
    CustomerController controller;

    private GetTransactionsResponseDTO responseDTO;
    private final String GET_TRANSACTION_URL = "/customer/{id}/transactions-history";

    @BeforeEach
    public void setUp() {
    }

    @Test
    void buscarHistorico_deveRetornar200Ok() throws Exception {
        responseDTO = new GetTransactionsResponseDTO(new GetTransactionsResponseDTO.BalanceDTO(1, LocalDateTime.now(), 2), List.of());

        when(service.getTransactions("1")).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(GET_TRANSACTION_URL, "1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
   }

    @Test
    void buscarHistoricoDeUsuarioNaoExistente_deveRetornar422Ok() throws Exception {
        responseDTO = new GetTransactionsResponseDTO(new GetTransactionsResponseDTO.BalanceDTO(1, LocalDateTime.now(), 2), List.of());

        when(service.getTransactions("1")).thenThrow(new UserNotFoundException("1"));

        mockMvc.perform(MockMvcRequestBuilders.get(GET_TRANSACTION_URL, "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", equalTo("User 1 not found")));
    }
}
