package com.server.cservice.service;
import com.server.cservice.domain.model.Card;
import com.server.cservice.domain.model.Transaction;
import com.server.cservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;
    private final CardService cardService;
    
    @Transactional
    public void create(Transaction transaction) {
        Card from = cardService.getByNumberAndDateAndCvv(
            transaction.getFrom().getNumber(), 
            transaction.getFrom().getDate(), 
            transaction.getFrom().getCvv()
        );
        Card to = cardService.getByNumberAndDate(
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
