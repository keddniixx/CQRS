package com.server.common.service.card;

import com.server.common.domain.exception.RAE;
import com.server.common.domain.exception.RNF;
import com.server.common.domain.model.Card;
import com.server.common.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardQueryServiceImpl implements CardQueryService {
    private final CardRepository repository;

    @Override
    public Card getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(RNF::new);
    }

    @Override
    public boolean existsByNumberAndDate(String number, String date) {
        return repository.existsByNumberAndDate(number,date);
    }

    @Override
    public Card getByNumberAndDateAndCvv(
            String number,
            String date,
            String cvv
    ) {
        return repository.getByNumberAndDateAndCvv(number, date, cvv)
                .orElseThrow(RNF::new);
    }

    @Override
    public Card getByNumberAndDate(
            final String number,
            final String date
    ) {
        return repository.findByNumberAndDate(number, date)
                .orElseThrow(RAE::new);
    }
}
