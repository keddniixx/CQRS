package com.server.cservice.service.card;

import com.server.common.service.card.CardQueryService;
import com.server.cservice.service.client.ClientService;
import com.server.common.domain.model.Card;
import com.server.common.domain.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl
        implements CardService {

    private final ClientService clientService;
    private final CardQueryService queryService;
    private final CardCommandService commandService;

    @Override
    public void create(Card object) {
        commandService.create(object);
    }

    @Override
    public Card getById(UUID id) {
        return queryService.getById(id);
    }

    @Override
    public void createByClientId(UUID id) {
        Client client = clientService.getById(id);
        Card card = new Card(client.getAccount());
        commandService.create(card);
    }

    @Override
    public boolean existsByNumberAndDate(String number, String date) {
        return queryService.existsByNumberAndDate(number, date);
    }

    @Override
    public Card getByNumberAndDateAndCvv(String number, String date, String cvv) {
        return queryService.getByNumberAndDateAndCvv(number, date, cvv);
    }

    @Override
    public Card getByNumberAndDate(
            final String number,
            final String date
    ) {
        return queryService.getByNumberAndDate(number, date);
    }
}
