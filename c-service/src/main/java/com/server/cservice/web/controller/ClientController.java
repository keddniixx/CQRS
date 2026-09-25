package com.server.cservice.web.controller;


import com.server.common.domain.model.Account;
import com.server.common.domain.model.Card;
import com.server.common.domain.model.Client;
import com.server.cservice.service.client.ClientService;
import com.server.cservice.web.dto.AccountDto;
import com.server.cservice.web.dto.CardDto;
import com.server.cservice.web.dto.ClientDto;
import com.server.cservice.web.dto.mapper.AccountMapper;
import com.server.cservice.web.dto.mapper.CardMapper;
import com.server.cservice.web.dto.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final CardMapper cardMapper;
    private final AccountMapper accountMapper;

    @GetMapping("/{id}")
    @PreAuthorize("@ssi.canAccessClient(#id)")
    public ClientDto getById(
            @PathVariable final UUID id
    ) {
        Client client = clientService.getById(id);
        return clientMapper.toDto(client);
    }

    @GetMapping("/{id}/cards")
    @PreAuthorize("@ssi.canAccessClient(#id)")
    public List<CardDto> getCardsById(
            @PathVariable final UUID id
    ){
        Client client = clientService.getById(id);
        List<Card> cards = client.getCards();
        return cardMapper.toDto(cards);
    }

    @GetMapping("/{id}/account")
    @PreAuthorize("@ssi.canAccessClient(#id)")
    public AccountDto getAccountsById(
            @PathVariable final  UUID id
    ){
        Client client = clientService.getById(id);
        Account account = client.getAccount();
        return accountMapper.toDto(account);

    }
}
