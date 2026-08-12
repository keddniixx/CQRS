package com.server.cservice.web.controller;

import com.server.cservice.domain.model.Account;
import com.server.cservice.domain.model.Card;
import com.server.cservice.domain.model.Client;
import com.server.cservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public void create(@RequestBody Client client) {
        clientService.create(client);
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable UUID id) {
        return clientService.getById(id);
    }

    @GetMapping("/{id}/cards")
    public List<Card> getCardsById(@PathVariable UUID id) {
        Client client = clientService.getById(id);
        return client.getCards();
    }

    @GetMapping("/{id}/account")
    public Account getAccountById(@PathVariable UUID id) {
        Client client = clientService.getById(id);
        return client.getAccount();
    }
}
