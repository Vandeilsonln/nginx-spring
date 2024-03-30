package com.example.teste.controller;

import com.example.teste.dto.request.ClienteTransacaoRequestDTO;
import com.example.teste.dto.response.ClienteTransacaoResponseDTO;
import com.example.teste.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(final TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("{id}/transacoes")
    public ResponseEntity<ClienteTransacaoResponseDTO> criarTransacao(
        @PathVariable final String id, @Valid @RequestBody ClienteTransacaoRequestDTO requestDTO) {

        var response = transacaoService.criarTransacao(requestDTO);

        return ResponseEntity.ok(response);
    }

}
