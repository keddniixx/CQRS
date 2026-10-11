package com.server.eventhandler.service.client;

import com.server.common.domain.model.Account;
import com.server.common.domain.model.Card;
import com.server.common.domain.model.Client;
import com.server.common.repository.ClientRepository;
import com.server.eventhandler.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final AccountService accountService;

    @Override
    public Client create(
            Client client
    ) {
        Account account = new Account();
        account = accountService.create(account);
        client.setAccount(account);
        return repository.save(client);
    }

    @Override
    public void addCard(UUID clientId,
                        UUID cardId
    ) {
        repository.addCard(
                clientId.toString(),
                cardId.toString()
        );
    }
}
