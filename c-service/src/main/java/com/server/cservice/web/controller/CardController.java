package com.server.cservice.web.controller;

import com.server.cservice.domain.model.Card;
import com.server.cservice.domain.model.Transaction;
import com.server.cservice.service.command.CardCommandService;
import com.server.cservice.service.query.CardQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardCommandService cardCommandService;
    private final CardQueryService cardQueryService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public void create(@RequestParam UUID clientId) {
        cardCommandService.createByClientId(clientId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Card getById(@PathVariable UUID id) {
        return cardQueryService.getById(id);
    }

    @GetMapping("/{id}/transactions")
    @PreAuthorize("isAuthenticated()")
    public List<Transaction> getTransactionsById(@PathVariable UUID id) {
        Card card = cardQueryService.getById(id);
        return card.getTransactions();
    }
}
