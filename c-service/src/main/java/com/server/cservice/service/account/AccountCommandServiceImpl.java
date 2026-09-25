package com.server.cservice.service.account;

import com.server.cservice.service.event.EventService;
import com.server.common.domain.model.Account;
import com.server.common.events.AccountCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountCommandServiceImpl
        implements AccountCommandService  {

    private final EventService eventService;


    public void create(Account object) {
        AccountCreateEvent event = new AccountCreateEvent(object);
        eventService.create(event);
    }
}
