package com.example.teste.controller;

import com.example.teste.service.ExtratoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ExtratoController {

    private final ExtratoService service;

    public ExtratoController(final ExtratoService service) {
        this.service = service;
    }

    @GetMapping("{id}/extrato")
    public ResponseEntity<?> getExtrato(@PathVariable final String id) {
        return ResponseEntity.ok(Void.class);
    }

}
