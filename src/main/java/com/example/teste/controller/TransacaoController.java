package com.example.teste.controller;

import com.example.teste.dto.request.ClienteTransacaoRequestDTO;
import com.example.teste.dto.response.TransacaoResponseDTO;
import com.example.teste.entity.TransacaoEntity;
import com.example.teste.service.TransacaoService;
import com.example.teste.service.TransacaoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoServiceImpl;

    @PostMapping("{id}/transacoes")
    public ResponseEntity<TransacaoResponseDTO> criarTransacao(
        @PathVariable final String id, @Valid @RequestBody ClienteTransacaoRequestDTO requestDTO) {

        var response = transacaoServiceImpl.criarTransacao(id, requestDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TransacaoEntity>> todosClientes() {
        return ResponseEntity.ok(transacaoServiceImpl.todosClientes());
    }

}
