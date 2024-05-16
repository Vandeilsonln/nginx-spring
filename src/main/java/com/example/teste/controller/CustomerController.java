package com.example.teste.controller;

import com.example.teste.dto.response.GetTransactionsResponseDTO;
import com.example.teste.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("{id}/transactions-history")
    public ResponseEntity<GetTransactionsResponseDTO> getTransactions(@PathVariable final String id) {
        return ResponseEntity.ok(customerService.getTransactions(id));
    }

}
