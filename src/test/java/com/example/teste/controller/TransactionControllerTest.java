package com.example.teste.controller;

import com.example.teste.dto.request.CreateTransactionRequestDTO;
import com.example.teste.dto.response.TransactionResponseDTO;
import com.example.teste.exception.ApiErrorResponse;
import com.example.teste.exception.ApiExceptionHandler;
import com.example.teste.service.TransactionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest({TransacaoController.class, ApiErrorResponse.class, ApiExceptionHandler.class})
@AutoConfigureMockMvc
class TransactionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TransactionServiceImpl service;

    @InjectMocks
    TransacaoController controller;

    private CreateTransactionRequestDTO requestDTO;
    private TransactionResponseDTO responseDTO;
    private ObjectMapper mapper = new ObjectMapper();
    private final String CREATE_TRANSACTION_URL = "/transaction/{id}";

    @BeforeEach
    public void setUp() {
    }

    @Test
    void criarTransacao_comPayloadCorreto_deveRetornar200Ok() throws Exception {
        requestDTO = new CreateTransactionRequestDTO(200, "c", "descrição");
        responseDTO = new TransactionResponseDTO(200, 200);

        when(service.createTransaction("1", requestDTO)).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_TRANSACTION_URL, "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestDTO)))
            .andExpect(MockMvcResultMatchers.status().isOk());
   }

    @ParameterizedTest
    @CsvSource(
        {"-2, c, desc",
        "2, cd, desc",
        "2, c, verylongedescription"})
    void criarTransacao_comPayloadInCorreto_deveRetornar422(Integer valor, String tipo, String descricao) throws Exception {
        requestDTO = new CreateTransactionRequestDTO(valor, tipo, descricao);
        responseDTO = new TransactionResponseDTO(200, 200);

        when(service.createTransaction("1", requestDTO)).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_TRANSACTION_URL, "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestDTO)))
            .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }


}
