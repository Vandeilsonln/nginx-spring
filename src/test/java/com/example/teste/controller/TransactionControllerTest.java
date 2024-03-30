package com.example.teste.controller;

import com.example.teste.dto.request.ClienteTransacaoRequestDTO;
import com.example.teste.dto.response.ClienteTransacaoResponseDTO;
import com.example.teste.exception.ApiErrorResponse;
import com.example.teste.exception.ApiExceptionHandler;
import com.example.teste.service.TransacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
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
    TransacaoService service;

    @InjectMocks
    TransacaoController controller;

    private ClienteTransacaoRequestDTO requestDTO;
    private ClienteTransacaoResponseDTO responseDTO;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
    }

    @Test
    void criarTransacao_comPayloadCorreto_deveRetornar200Ok() throws Exception {
        requestDTO = new ClienteTransacaoRequestDTO(200, "c", "descrição");
        responseDTO = new ClienteTransacaoResponseDTO(200, 200);

        when(service.criarTransacao(requestDTO)).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes/{id}/transacoes", "1")
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
        requestDTO = new ClienteTransacaoRequestDTO(valor, tipo, descricao);
        responseDTO = new ClienteTransacaoResponseDTO(200, 200);

        when(service.criarTransacao(requestDTO)).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes/{id}/transacoes", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestDTO)))
            .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }


}