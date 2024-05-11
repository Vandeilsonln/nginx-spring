package com.example.teste.service;

import com.example.teste.dto.request.ClienteTransacaoRequestDTO;
import com.example.teste.dto.response.TransacaoResponseDTO;
import com.example.teste.entity.ClienteEntity;
import com.example.teste.entity.TransacaoEntity;
import com.example.teste.exception.LimiteEstouradoException;
import com.example.teste.exception.UserNotFoundException;
import com.example.teste.repository.ClienteRepository;
import com.example.teste.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Override
    public TransacaoResponseDTO criarTransacao(final String id, final ClienteTransacaoRequestDTO requestDTO) {
        var cliente = getCliente(id);

        if (isDebito(requestDTO)) {
            var novoSaldo = processarDebito(requestDTO, cliente);
            cliente.setSaldo(novoSaldo);
            clienteRepository.updateSaldo(novoSaldo, Integer.valueOf(id));
        }
        transacaoRepository.save(new TransacaoEntity(cliente, requestDTO.amount(), requestDTO.tipo(), LocalDateTime.now()));
        return new TransacaoResponseDTO(cliente.getLimite(), cliente.getSaldo());
    }

    private ClienteEntity getCliente(String id) {
        return clienteRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new UserNotFoundException(id));
    }

    private boolean isDebito(ClienteTransacaoRequestDTO requestDTO) {
        return "D".equals(requestDTO.tipo().toUpperCase(Locale.ROOT));
    }

    private int processarDebito(ClienteTransacaoRequestDTO requestDTO, ClienteEntity cliente) {
        var novoSaldo = calculateNovoSaldo(requestDTO, cliente);
        if (novoSaldoExtrapolateLimite(novoSaldo)) {
            throw new LimiteEstouradoException();
        }
        return novoSaldo;
    }

    private int calculateNovoSaldo(ClienteTransacaoRequestDTO requestDTO, ClienteEntity cliente) {
        return cliente.getSaldo() - requestDTO.amount();
    }

    private boolean novoSaldoExtrapolateLimite(int novoSaldo) {
        return novoSaldo < 0;
    }

    @Override
    public List<TransacaoEntity> todosClientes() {
        return transacaoRepository.findAll();
    }

}
