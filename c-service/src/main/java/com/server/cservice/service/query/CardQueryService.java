package com.server.cservice.service.query;

import com.server.cservice.domain.model.Card;
import com.server.cservice.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardQueryService {
    private final CardRepository repository;
    
    public Card getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Card not found"));
    }

    public Card getByNumberAndDate(String number, String date) {
        return repository.findByNumberAndDate(number, date)
            .orElseThrow(() -> new RuntimeException("Card not found"));
    }

    public Card getByNumberAndDateAndCvv(String number, String date, String cvv) {
        return repository.findByNumberAndDateAndCvv(number, date, cvv)
            .orElseThrow(() -> new RuntimeException("Card not found"));
    }
}
