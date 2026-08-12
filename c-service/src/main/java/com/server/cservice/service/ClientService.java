package com.server.cservice.service;
import com.server.cservice.domain.model.Account;
import com.server.cservice.domain.model.Client;
import com.server.cservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;
    private final AccountService accountService;
    
    @Transactional
    public void create(Client client) {
        if (repository.existsByUsername(client.getUsername())) {
            throw new RuntimeException("Client already exists.");
        }
        Account account = accountService.create();
        client.setAccount(account);
        repository.save(client);
    }

    public Client getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
    }
}
