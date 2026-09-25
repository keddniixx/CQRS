package com.server.cservice.service.account;


import com.server.common.domain.model.Account;
import com.server.common.service.QueryService;
import com.server.cservice.service.CommandSerivice;

public interface AccountService
        extends QueryService<Account>, CommandSerivice<Account> {

}
