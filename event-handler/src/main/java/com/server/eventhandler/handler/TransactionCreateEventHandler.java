package com.server.eventhandler.handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.server.common.domain.model.Account;
import com.server.common.domain.model.Transaction;
import com.server.common.events.AccountCreateEvent;
import com.server.common.events.TransactionCreateEvent;
import com.server.eventhandler.service.account.AccountService;
import com.server.eventhandler.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("TRANSACTION_CREATE")
@RequiredArgsConstructor
public class TransactionCreateEventHandler implements EventHandler{

    private final TransactionService transactionService;
    private final Gson gson;

    @Override
    @Transactional
    public void handle(JsonObject object, Acknowledgment acknowledgment) {
        TransactionCreateEvent event = gson.fromJson(object, TransactionCreateEvent.class);
        Transaction transaction = gson.fromJson((String) event.getPayload(), Transaction.class);
        transactionService.create(transaction);
        acknowledgment.acknowledge();
    }
}
