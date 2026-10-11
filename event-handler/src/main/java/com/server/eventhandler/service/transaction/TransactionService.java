package com.server.eventhandler.service.transaction;

import com.server.common.domain.model.Transaction;

public interface TransactionService {
    Transaction create(Transaction transaction);
}
