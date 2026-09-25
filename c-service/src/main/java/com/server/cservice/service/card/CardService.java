package com.server.cservice.service.card;

import com.server.common.service.QueryService;
import com.server.cservice.service.CommandSerivice;
import com.server.common.domain.model.Card;

import java.util.UUID;

public interface CardService
        extends QueryService<Card>, CommandSerivice<Card> {

    void createByClientId(UUID id);

    boolean existsByNumberAndDate(
            String number,
            String date
    );

    Card getByNumberAndDateAndCvv(String number, String date, String cvv);

    Card getByNumberAndDate(String number, String date);
}
