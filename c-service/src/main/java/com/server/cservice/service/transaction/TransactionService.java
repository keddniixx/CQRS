package com.server.cservice.service.transaction;


import com.server.common.domain.model.Transaction;
import com.server.common.service.QueryService;
import com.server.cservice.service.CommandSerivice;

public interface TransactionService
        extends QueryService<Transaction>, CommandSerivice<Transaction> {

}
