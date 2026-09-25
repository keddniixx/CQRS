package com.server.common.service.transaction;

import com.server.common.domain.exception.RNF;
import com.server.common.domain.model.Transaction;
import com.server.common.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionQueryServiceImpl implements TransactionQueryService {

    private final TransactionRepository repository;

    @Override
    public Transaction getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(RNF::new);
    }
}
