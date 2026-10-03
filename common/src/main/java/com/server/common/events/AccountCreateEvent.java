package com.server.common.events;


import com.server.common.domain.aggregate.Aggregate;
import com.server.common.domain.model.Account;

public class AccountCreateEvent extends AbstractEvent {

    public AccountCreateEvent(Account account){
        super(null,EventType.ACCOUNT_CREATE,account);
    }


    @Override
    public void apply(Aggregate aggregate) {
    }
}
