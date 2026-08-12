package com.server.cservice.web.controller;

import com.server.cservice.domain.model.Transaction;
import com.server.cservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public void create(@RequestBody Transaction transaction) {
        transactionService.create(transaction);
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable UUID id) {
        // Mock method for raw entity
        return null;
    }
}
