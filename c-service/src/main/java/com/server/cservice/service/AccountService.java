package com.server.cservice.service;

import com.server.cservice.domain.model.Account;
import com.server.cservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository repository;
    
    @Transactional
    public Account create() {
        Account account = new Account();
        account.setBalance(BigDecimal.ZERO);
        account.setNumber(UUID.randomUUID().toString());
        return repository.save(account);
    }
}
