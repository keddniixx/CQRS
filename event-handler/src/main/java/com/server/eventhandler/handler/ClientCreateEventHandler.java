package com.server.eventhandler.handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.server.common.domain.model.Card;
import com.server.common.domain.model.Client;
import com.server.common.events.CardCreateEvent;
import com.server.common.events.ClientCreateEvent;
import com.server.eventhandler.service.card.CardService;
import com.server.eventhandler.service.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("CLIENT_CREATE")
@RequiredArgsConstructor
public class ClientCreateEventHandler implements EventHandler{

    private final ClientService clientService;
    private final Gson gson;

    @Override
    @Transactional
    public void handle(JsonObject object, Acknowledgment acknowledgment) {
        ClientCreateEvent event = gson.fromJson(object, ClientCreateEvent.class);
        Client client = gson.fromJson((String) event.getPayload(), Client.class);
        clientService.create(client);
        acknowledgment.acknowledge();
    }
}
