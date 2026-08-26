package com.server.cservice.web.controller;

import com.server.cservice.domain.dto.ClientDto;
import com.server.cservice.domain.model.Account;
import com.server.cservice.domain.model.Card;
import com.server.cservice.domain.model.Client;
import com.server.cservice.service.command.ClientCommandService;
import com.server.cservice.service.query.ClientQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientCommandService clientCommandService;
    private final ClientQueryService clientQueryService;

    @PostMapping
    public void create(@Validated @RequestBody ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setUsername(clientDto.getUsername());
        client.setPassword(clientDto.getPassword());
        clientCommandService.create(client);
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable UUID id) {
        return clientQueryService.getById(id);
    }

    @GetMapping("/{id}/cards")
    public List<Card> getCardsById(@PathVariable UUID id) {
        Client client = clientQueryService.getById(id);
        return client.getCards();
    }

    @GetMapping("/{id}/account")
    public Account getAccountById(@PathVariable UUID id) {
        Client client = clientQueryService.getById(id);
        return client.getAccount();
    }
}
