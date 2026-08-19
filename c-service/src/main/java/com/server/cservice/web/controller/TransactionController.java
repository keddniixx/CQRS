package com.server.cservice.web.controller;

import com.server.cservice.domain.model.Transaction;
import com.server.cservice.service.command.TransactionCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionCommandService transactionCommandService;

    @PostMapping
    public void create(@RequestBody Transaction transaction) {
        transactionCommandService.create(transaction);
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable UUID id) {
        // Query logic would be here
        return null;
    }
}
