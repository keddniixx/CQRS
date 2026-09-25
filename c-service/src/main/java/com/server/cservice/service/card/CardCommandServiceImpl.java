package com.server.cservice.service.card;

import com.server.cservice.service.event.EventService;
import com.server.common.domain.model.Card;
import com.server.common.events.CardCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CardCommandServiceImpl
        implements CardCommandService {

    private final EventService eventService;


    @Override
    public void create(Card object) {
        CardCreateEvent cardCreateEvent = new CardCreateEvent(object);
        eventService.create(cardCreateEvent);
    }
}
