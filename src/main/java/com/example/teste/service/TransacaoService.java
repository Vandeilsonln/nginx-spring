package com.example.teste.service;

import com.example.teste.dto.request.ClienteTransacaoRequestDTO;
import com.example.teste.dto.response.ClienteTransacaoResponseDTO;
import com.example.teste.entity.ClienteEntity;
import com.example.teste.entity.TransacaoEntity;

import java.util.List;

public interface TransacaoService {

    ClienteTransacaoResponseDTO criarTransacao(final String id, final ClienteTransacaoRequestDTO requestDTO);

    List<TransacaoEntity> todosClientes();

}
