package com.example.teste.service;

import com.example.teste.dto.request.ClienteTransacaoRequestDTO;
import com.example.teste.dto.response.TransacaoResponseDTO;
import com.example.teste.entity.TransacaoEntity;
import com.example.teste.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    @Override
    public TransacaoResponseDTO criarTransacao(final String id, final ClienteTransacaoRequestDTO requestDTO) {
        // buscar user no banco e tratar se n existir

        // comparar valor e saldo atual com base se é débito ou crédito
        return new TransacaoResponseDTO(requestDTO.amount(), requestDTO.amount());
    }

    @Override
    public List<TransacaoEntity> todosClientes() {
        return repository.findAll();
    }

}
