package com.server.cservice.web.controller;

import com.server.common.domain.model.Card;
import com.server.cservice.service.card.CardService;
import com.server.cservice.web.dto.CardDto;
import com.server.cservice.web.dto.TransactionDto;
import com.server.cservice.web.dto.mapper.CardMapper;
import com.server.cservice.web.dto.mapper.TransactionMapper;
import com.server.cservice.web.security.SecurityUser;
import com.server.cservice.web.security.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final CardMapper cardMapper;
    private final TransactionMapper transactionMapper;
    private final SecurityService securityService;


    @PostMapping
    public void create() {
        SecurityUser user = securityService.getUserFromRequest();
        cardService.createByClientId(user.getId());
    }

    @GetMapping("/{id}")
    @PreAuthorize("@ssi.canAccessCard(#id)")
    public CardDto getById(
            @PathVariable final UUID id
    ){
        Card card = cardService.getById(id);
        return cardMapper.toDto(card);
    }


    @GetMapping("/{id}/transactions")
    @PreAuthorize("@ssi.canAccessCard(#id)")
    public List<TransactionDto> getTransactionsById(
            @PathVariable final  UUID id
    ){
        Card card = cardService.getById(id);
        return transactionMapper.toDto(card.getTransactions());
    }

}
