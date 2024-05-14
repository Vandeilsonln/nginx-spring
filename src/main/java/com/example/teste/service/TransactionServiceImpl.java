package com.example.teste.service;

import com.example.teste.dto.request.CreateTransactionRequestDTO;
import com.example.teste.dto.response.TransacaoResponseDTO;
import com.example.teste.entity.ClienteEntity;
import com.example.teste.entity.TransacaoEntity;
import com.example.teste.exception.LimiteEstouradoException;
import com.example.teste.exception.UserNotFoundException;
import com.example.teste.repository.ClienteRepository;
import com.example.teste.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Override
    @Transactional
    public TransacaoResponseDTO createTransaction(final String id, final CreateTransactionRequestDTO requestDTO) {
        var cliente = getCliente(id);

        if (isDebito(requestDTO)) {
            var novoSaldo = processarDebito(requestDTO, cliente);
            cliente.setSaldo(novoSaldo);
            clienteRepository.updateSaldo(novoSaldo, Integer.valueOf(id));
        }
        transacaoRepository.save(new TransacaoEntity(cliente, requestDTO.amount(), requestDTO.type(), LocalDateTime.now()));
        return new TransacaoResponseDTO(cliente.getLimite(), cliente.getSaldo());
    }

    private ClienteEntity getCliente(String id) {
        return clienteRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new UserNotFoundException(id));
    }

    private boolean isDebito(CreateTransactionRequestDTO requestDTO) {
        return "D".equals(requestDTO.type().toUpperCase(Locale.ROOT));
    }

    private int processarDebito(CreateTransactionRequestDTO requestDTO, ClienteEntity cliente) {
        var novoSaldo = calculateNovoSaldo(requestDTO, cliente);
        if (novoSaldoExtrapolateLimite(novoSaldo)) {
            throw new LimiteEstouradoException();
        }
        return novoSaldo;
    }

    private int calculateNovoSaldo(CreateTransactionRequestDTO requestDTO, ClienteEntity cliente) {
        return cliente.getSaldo() - requestDTO.amount();
    }

    private boolean novoSaldoExtrapolateLimite(int novoSaldo) {
        return novoSaldo < 0;
    }

}
