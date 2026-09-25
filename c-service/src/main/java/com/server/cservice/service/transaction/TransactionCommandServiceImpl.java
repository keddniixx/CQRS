package com.server.cservice.service.transaction;

import com.server.common.domain.model.Card;
import com.server.common.domain.model.Transaction;
import com.server.common.events.TransactionCreateEvent;
import com.server.cservice.service.card.CardService;
import com.server.cservice.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class TransactionCommandServiceImpl
        implements TransactionCommandService {

    private final EventService eventService;
    private final CardService cardService;

    @Override
    public void create(
            final Transaction object
    ) {
        Card from = cardService.getByNumberAndDateAndCvv(
                object.getFrom().getNumber(),
                object.getFrom().getDate(),
                object.getFrom().getCvv()
        );
        Card to = cardService.getByNumberAndDate(
                object.getTo().getNumber(),
                object.getTo().getDate()
        );
        object.setFrom(from);
        object.setTo(to);
        TransactionCreateEvent event = new TransactionCreateEvent(object);
        eventService.create(event);
    }

}