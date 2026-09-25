package com.server.common.service.account;

import com.server.common.domain.exception.RNF;
import com.server.common.domain.model.Account;
import com.server.common.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountQueryServiceImpl
        implements AccountQueryService {

    private final AccountRepository repository;

    @Override
    public Account getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(RNF::new);
    }
}
