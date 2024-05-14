package com.example.teste.controller;

import com.example.teste.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping("{id}/transactions-history")
    public ResponseEntity<?> getTransactions(@PathVariable final String id) {
        return ResponseEntity.ok(consumerService.getTransactions(id));
    }

}
