package com.server.common.service.card;

import com.server.common.domain.model.Card;
import com.server.common.service.QueryService;

public interface CardQueryService extends QueryService<Card> {
    boolean existsByNumberAndDate(String number, String date);

    Card getByNumberAndDateAndCvv(
            String number,
            String date,
            String cvv
    );

    Card getByNumberAndDate(String number, String date);
}
