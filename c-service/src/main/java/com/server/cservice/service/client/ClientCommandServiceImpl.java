package com.server.cservice.service.client;

import com.server.cservice.service.event.EventService;
import com.server.common.domain.model.Client;
import com.server.common.events.ClientCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientCommandServiceImpl implements ClientCommandService {

    private final EventService eventService;

    @Override
    public void create(Client object) {
        ClientCreateEvent clientCreateEvent = new ClientCreateEvent(object);
        eventService.create(clientCreateEvent);
    }
}
