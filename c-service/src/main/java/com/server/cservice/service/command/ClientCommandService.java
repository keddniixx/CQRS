package com.server.cservice.service.command;

import com.server.cservice.domain.model.Account;
import com.server.cservice.domain.model.Client;
import com.server.cservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientCommandService {
    private final ClientRepository repository;
    private final AccountCommandService accountCommandService;
    
    @Transactional
    public void create(Client client) {
        if (repository.existsByUsername(client.getUsername())) {
            throw new RuntimeException("Client already exists.");
        }
        Account account = accountCommandService.create();
        client.setAccount(account);
        repository.save(client);
    }
}
