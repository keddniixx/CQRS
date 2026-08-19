package com.server.cservice.service.command;

import com.server.cservice.domain.model.Card;
import com.server.cservice.domain.model.Client;
import com.server.cservice.repository.CardRepository;
import com.server.cservice.service.query.ClientQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardCommandService {
    private final CardRepository repository;
    private final ClientQueryService clientQueryService;
    
    @Transactional
    public void createByClientId(UUID clientId) {
        Client client = clientQueryService.getById(clientId);
        Card card = new Card();
        card.setAccount(client.getAccount());
        card.setNumber(UUID.randomUUID().toString().substring(0, 16));
        card.setDate("12/30");
        card.setCvv("123");
        Card savedCard = repository.save(card);
        client.getCards().add(savedCard);
    }
}
