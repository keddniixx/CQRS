package com.server.cservice.service.command;

import com.server.cservice.domain.model.Card;
import com.server.cservice.domain.model.Transaction;
import com.server.cservice.repository.TransactionRepository;
import com.server.cservice.service.query.CardQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionCommandService {
    private final TransactionRepository repository;
    private final CardQueryService cardQueryService;
    
    @Transactional
    public void create(Transaction transaction) {
        Card from = cardQueryService.getByNumberAndDateAndCvv(
            transaction.getFrom().getNumber(), 
            transaction.getFrom().getDate(), 
            transaction.getFrom().getCvv()
        );
        Card to = cardQueryService.getByNumberAndDate(
            transaction.getTo().getNumber(), 
            transaction.getTo().getDate()
        );
        
        if (from.getAccount().getBalance().compareTo(transaction.getAmount()) < 0) {
            throw new RuntimeException("Not enough money.");
        }
        
        from.getAccount().setBalance(from.getAccount().getBalance().subtract(transaction.getAmount()));
        to.getAccount().setBalance(to.getAccount().getBalance().add(transaction.getAmount()));
        
        transaction.setFrom(from);
        transaction.setTo(to);
        
        repository.save(transaction);
        from.getTransactions().add(transaction);
        to.getTransactions().add(transaction);
    }
}
