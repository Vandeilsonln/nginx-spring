package com.example.teste.service;

import com.example.teste.dto.request.ClienteTransacaoRequestDTO;
import com.example.teste.dto.response.ClienteTransacaoResponseDTO;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransacaoService {

    public ClienteTransacaoResponseDTO criarTransacao(final ClienteTransacaoRequestDTO requestDTO) {
        return new ClienteTransacaoResponseDTO(requestDTO.valor(), requestDTO.valor());
    }

}
