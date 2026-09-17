package com.server.cservice.web.controller;

import com.server.cservice.domain.dto.TransactionDto;
import com.server.cservice.domain.model.Card;
import com.server.cservice.domain.model.Transaction;
import com.server.cservice.service.command.TransactionCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionCommandService transactionCommandService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public void create(@Validated @RequestBody TransactionDto dto) {
        Transaction transaction = new Transaction();
        Card from = new Card();
        from.setNumber(dto.getFrom().getNumber());
        from.setDate(dto.getFrom().getDate());
        from.setCvv(dto.getFrom().getCvv());
        
        Card to = new Card();
        to.setNumber(dto.getTo().getNumber());
        to.setDate(dto.getTo().getDate());
        
        transaction.setFrom(from);
        transaction.setTo(to);
        transaction.setAmount(dto.getAmount());
        
        transactionCommandService.create(transaction);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Transaction getById(@PathVariable UUID id) {
        return null;
    }
}
