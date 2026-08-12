package com.server.cservice.service;
import com.server.cservice.domain.model.Card;
import com.server.cservice.domain.model.Client;
import com.server.cservice.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository repository;
    private final ClientService clientService;
    
    @Transactional
    public void createByClientId(UUID clientId) {
        Client client = clientService.getById(clientId);
        Card card = new Card();
        card.setAccount(client.getAccount());
        card.setNumber(UUID.randomUUID().toString().substring(0, 16));
        card.setDate("12/30");
        card.setCvv("123");
        Card savedCard = repository.save(card);
        client.getCards().add(savedCard);
    }
    
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
