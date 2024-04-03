package com.example.teste.service;

import com.example.teste.dto.request.ClienteTransacaoRequestDTO;
import com.example.teste.dto.response.ClienteTransacaoResponseDTO;
import com.example.teste.entity.ClienteEntity;
import com.example.teste.entity.TransacaoEntity;
import com.example.teste.repository.ClienteRepository;
import com.example.teste.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    @Override
    public ClienteTransacaoResponseDTO criarTransacao(final String id, final ClienteTransacaoRequestDTO requestDTO) {
        return new ClienteTransacaoResponseDTO(requestDTO.amount(), requestDTO.amount());
    }

    @Override
    public List<TransacaoEntity> todosClientes() {
        return repository.findAll();
    }

}
