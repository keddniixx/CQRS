package com.server.eventhandler.service.card;

import com.server.common.domain.model.Card;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

public interface CardService {
    Card create(Card card);
    Card getById(UUID id);

    @Transactional
    void add(
            Card card,
            BigDecimal amount
    );

    @Transactional
    void addTransaction(
            final UUID cardId,
            final UUID transactionId
    );

    Card getByNumberAndDateAndCvv(
            String number,
            String date,
            String cvv);
}
