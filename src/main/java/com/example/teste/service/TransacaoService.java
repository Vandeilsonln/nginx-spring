package com.example.teste.service;

import com.example.teste.dto.request.ClienteTransacaoRequestDTO;
import com.example.teste.dto.response.TransacaoResponseDTO;
import com.example.teste.entity.TransacaoEntity;

import java.util.List;

public interface TransacaoService {

    TransacaoResponseDTO criarTransacao(final String id, final ClienteTransacaoRequestDTO requestDTO);

    List<TransacaoEntity> todosClientes();

}
