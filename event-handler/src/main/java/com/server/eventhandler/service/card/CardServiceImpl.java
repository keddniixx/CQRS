package com.server.eventhandler.service.card;

import com.server.common.domain.exception.RAE;
import com.server.common.domain.model.Card;
import com.server.common.domain.model.Client;
import com.server.common.repository.CardRepository;
import com.server.common.service.card.CardQueryService;
import com.server.common.service.client.ClientQueryService;
import com.server.eventhandler.service.client.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository repository;
    private final ClientQueryService clientQueryService;
    private final ClientService clientService;
    private final CardQueryService cardQueryService;

    @Override
    public Card getById(
            final UUID id
    ) {
        return repository.findById(id)
                .orElseThrow(RAE::new);
    }

    @Override
    public Card create(
            final Card card
    ) {
        card.setCvv(generateCvv());
        card.setDate(generateDate());
        card.setNumber(generateNumber());
        repository.save(card);
        Client client = clientQueryService.getByAccount(
                card.getAccount().getId()
        );
        clientService.addCard(client.getId(), card.getId());
        return card;
    }

    private String generateCvv() {
        return String.valueOf(100 + (int) (Math.random() * 899));
    }

    private String generateDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = currentDate.plusYears(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        return expirationDate.format(formatter);
    }

    private String generateNumber() {
        return String.format(
                "%04d%04d%04d%04d",
                1000 + (int) (Math.random() * 8999),
                1000 + (int) (Math.random() * 8999),
                1000 + (int) (Math.random() * 8999),
                1000 + (int) (Math.random() * 8999)
        );
    }

    @Transactional
    @Override
    public void add(
            final Card card,
            final BigDecimal amount
    ) {
        card.getAccount().setBalance(
                card.getAccount().getBalance().add(amount)
        );
        repository.save(card);
    }

    @Override
    @Transactional
    public void addTransaction(
            final UUID cardId,
            final UUID transactionId
    ) {
        repository.addTransaction(
                cardId.toString(),
                transactionId.toString()
        );
    }


    @Override
    public Card getByNumberAndDateAndCvv(String number, String date, String cvv) {
        return cardQueryService.getByNumberAndDateAndCvv(number, date, cvv);
    }
}
